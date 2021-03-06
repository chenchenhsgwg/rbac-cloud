package com.bosssoft.trainee.admin.server.biz;

import com.bosssoft.trainee.admin.api.dto.MenuButtonCodeDTO;
import com.bosssoft.trainee.admin.api.dto.MenuButtonDTO;
import com.bosssoft.trainee.admin.api.dto.UserInfoDTO;
import com.bosssoft.trainee.admin.api.model.SysMenu;
import com.bosssoft.trainee.admin.api.model.SysMenuButton;
import com.bosssoft.trainee.admin.api.model.SysRole;
import com.bosssoft.trainee.admin.api.model.SysRoleAuthorization;
import com.bosssoft.trainee.admin.api.vo.SysRoleTreeVO;
import com.bosssoft.trainee.admin.server.mapper.SysRoleMapper;
import com.bosssoft.trainee.base.core.biz.BaseBusinessBiz;
import com.bosssoft.trainee.base.common.constant.CommonConstant;
import com.bosssoft.trainee.base.common.constant.HttpStatusConstant;
import com.bosssoft.trainee.base.common.constant.InitialCapacityConstant;
import com.bosssoft.trainee.base.common.constant.RedisKeysConstant;
import com.bosssoft.trainee.base.common.exception.BusinessException;
import com.bosssoft.trainee.base.common.util.BeanUtil;
import com.bosssoft.trainee.base.common.util.TreeUtil;
import com.bosssoft.trainee.base.common.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysRoleBiz extends BaseBusinessBiz<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleAuthorizationBiz roleAuthorizationBiz;

    @Autowired
    private SysMenuBiz sysMenuBiz;

    @Autowired
    private SysMenuButtonBiz menuButtonBiz;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean insertModel(SysRole entity) {
        BeanUtil.beanAttributeValueTrim(entity);
        entity.setId(UUIDUtil.randomUUID());
        return super.insertModel(entity);
    }

    /**
     * ????????????
     *
     * @param id ??????id
     */
    @Override
    public boolean removeById(Serializable id) {
        if (null == id) {
            throw new BusinessException("???????????????????????????", HttpStatusConstant.FAIL);
        }
        int sons = this.getSonCountByParentId(id.toString());
        if (sons > 0) {
            throw new BusinessException("???????????????????????????????????????", HttpStatusConstant.FAIL);
        }
        SysRole sysRole = new SysRole();
        sysRole.setRoleCode(sysRole.getId() + "_" + sysRole.getRoleCode());
        sysRole.setId(id.toString());
        sysRole.setIsDeleted(CommonConstant.IS_FALSE);
        return super.updateById(sysRole);
    }

    /**
     * ??????????????????id?????????????????????
     *
     * @param parentId ????????????id
     */
    public int getSonCountByParentId(String parentId) {
        if (StringUtils.isBlank(parentId)) {
            return 0;
        }
        SysRole sysRole = new SysRole();
        sysRole.setParentId(parentId);
        sysRole.setIsDeleted(CommonConstant.IS_TRUE);
        return this.selectCount(sysRole);
    }

    /**
     * ???????????????
     */
    public List<SysRoleTreeVO> getSysRoleTree() {
        String[] fields = {"id", "parent_id", "role_code", "role_name", "description"};
        // ??????????????????
        List<SysRole> dictTypeList = this.selectFieldListAll(fields);
        // ????????????????????????
        List<SysRoleTreeVO> tree = new ArrayList<>();
        // ????????????
        if (BeanUtil.isNotEmpty(dictTypeList)) {
            for (SysRole sysRole : dictTypeList) {
                // ???????????????
                tree.add(new SysRoleTreeVO(sysRole.getId(), sysRole.getParentId(), sysRole.getRoleCode(),
                        sysRole.getRoleName(), sysRole.getDescription()));
            }
        }
        List<SysRoleTreeVO> result = TreeUtil.buildByRecursive(tree, CommonConstant.ROOT);
        return BeanUtil.isEmpty(result) ? new ArrayList<>() : result;
    }

    /**
     * ????????????????????????
     *
     * @return ???????????????????????????
     */
    public List<String> getRoles() {
        List<SysRole> roleList = super.selectFieldListAll(new String[]{"role_code"});
        List<String> roles = roleList.stream().map(role -> role.getRoleCode()).collect(Collectors.toList());
        return roles;
    }

    /**
     * ????????????????????????
     */
    public void refreshRoleCache() {
        List<String> roles = this.getRoles();
        for (String role : roles) {
            UserInfoDTO userInfoDTO = new UserInfoDTO();

            List<SysRoleAuthorization> roleAuthList = roleAuthorizationBiz.getRoleAuthByRoleCode(role);

            // ???????????????????????????ids?????????ids
            List<String> menuIdList = new ArrayList<>(InitialCapacityConstant.INITIAL_256_NUMBER);
            List<Integer> menuButtonIds = new ArrayList<>(InitialCapacityConstant.INITIAL_512_NUMBER);
            // ?????????????????????
            roleAuthList.forEach(roleAuth -> {
                if (SysRoleAuthorization.MENU_RESOURCE_TYPE.equals(roleAuth.getResourceType())) {
                    menuIdList.add(roleAuth.getResourceId());
                } else {
                    menuButtonIds.add(Integer.valueOf(roleAuth.getResourceId()));
                }
            });

            // ?????????????????????????????????
            List<SysMenu> sysMenuList = sysMenuBiz.getMenuListByMenuIds(menuIdList);
            // ?????????????????????????????????
            List<MenuButtonCodeDTO> menuButtonList = menuButtonBiz.getMenuCodeAndButCodeByButIds(menuButtonIds);

            this.refreshButtonAuth(userInfoDTO, sysMenuList, menuButtonList);

            this.refreshMenuButtonCache(role, menuButtonIds);

        }

        this.refreshAdminAuth();
    }

    /**
     * ????????????????????????
     *
     * @param role          ????????????
     * @param menuButtonIds ??????????????????
     */
    private void refreshMenuButtonCache(String role, List<Integer> menuButtonIds) {
        // ??????????????????????????????
        List<SysMenuButton> buttons = menuButtonBiz.getMenuButtonByIds(menuButtonIds);
        // ????????????????????????
        redisTemplate.opsForHash().put(RedisKeysConstant.ROLES_AUTH_KEY, role, buttons);
    }

    /**
     * ????????????????????????
     */
    private void refreshButtonAuth(UserInfoDTO userInfoDTO, List<SysMenu> sysMenuList, List<MenuButtonCodeDTO> menuButtonList) {
        // ?????????????????????????????????
        userInfoDTO.setMenuTree(sysMenuBiz.getMenuTreeSysMenuTree(sysMenuList));
        // ?????????????????????????????????
        userInfoDTO.setMenuButton(this.generateMenuButton(menuButtonList));
        // ??????????????????????????????
        redisTemplate.opsForHash().put(RedisKeysConstant.ROLES_BUTTON_AUTH_KEY, RedisKeysConstant.ADMIN_ROLES_AUTH_KEY, userInfoDTO);
    }

    /**
     * ???????????????????????????
     */
    private void refreshAdminAuth() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        // ????????????????????????
        List<SysMenu> sysMenuList = sysMenuBiz.getAllMenuList();
        List<MenuButtonCodeDTO> menuButtonList = menuButtonBiz.getAllMenuCodeAndButCode();
        this.refreshButtonAuth(userInfoDTO, sysMenuList, menuButtonList);
    }


    /**
     * ?????????????????????????????????json??????
     *
     * @param menuButtonList ???????????????????????????
     *                       ???????????????
     *                       [
     *                       menuCode1:butCode1
     *                       menuCode1:butCode2
     *                       ]
     * @return ???????????????
     * menuButton:
     * {
     * menuCode1:{ butCode1:1, butCode2:1, ... }???
     * menuCode2:{ butCode1:1, butCode2:1, ... },
     * ...
     * }
     */
    private MenuButtonDTO<String, MenuButtonDTO<String, Integer>> generateMenuButton(
            List<MenuButtonCodeDTO> menuButtonList) {
        MenuButtonDTO<String, MenuButtonDTO<String, Integer>> menuButton = new MenuButtonDTO<>();
        for (MenuButtonCodeDTO menuButtonCodeDTO : menuButtonList) {
            // ????????????
            String menuCode = menuButtonCodeDTO.getMenuCode();
            // ???????????????????????????
            if (StringUtils.isBlank(menuCode)) {
                continue;
            }
            // ????????????
            String buttonCode = menuButtonCodeDTO.getButtonCode();

            MenuButtonDTO<String, Integer> tmpeMenuButtonDTO = menuButton.get(menuCode);
            if (BeanUtil.isEmpty(tmpeMenuButtonDTO)) {
                tmpeMenuButtonDTO = new MenuButtonDTO<>();
            }
            // value????????????????????????????????????????????????
            tmpeMenuButtonDTO.put(buttonCode, 1);
            menuButton.put(menuCode, tmpeMenuButtonDTO);
        }
        return menuButton;
    }
}