package com.bosssoft.trainee.admin.server.biz;

import com.bosssoft.trainee.admin.api.dto.MenuTreeDTO;
import com.bosssoft.trainee.admin.api.model.SysMenu;
import com.bosssoft.trainee.admin.api.vo.SysMenuTreeVO;
import com.bosssoft.trainee.admin.server.mapper.SysMenuMapper;
import com.bosssoft.trainee.base.core.biz.BaseBusinessBiz;
import com.bosssoft.trainee.base.common.constant.CommonConstant;
import com.bosssoft.trainee.base.common.constant.HttpStatusConstant;
import com.bosssoft.trainee.base.common.constant.InitialCapacityConstant;
import com.bosssoft.trainee.base.common.exception.BusinessException;
import com.bosssoft.trainee.base.common.util.BeanUtil;
import com.bosssoft.trainee.base.common.util.TreeUtil;
import com.bosssoft.trainee.base.common.util.UUIDUtil;
import com.bosssoft.trainee.base.common.vo.ParamQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuBiz extends BaseBusinessBiz<SysMenuMapper, SysMenu> {

    @Override
    public boolean insertModel(SysMenu entity) {
        int count = this.getCountByMenuCode(entity.getMenuCode());
        if (count > 0) {
            throw new BusinessException("菜单编码已存在，请重新输入！");
        }
        entity.setId(UUIDUtil.randomUUID());
        return super.insertModel(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        if (null == id) {
            throw new BusinessException("请选择待删除对象！", HttpStatusConstant.FAIL);
        }
        int sons = this.getSonCountByMenuId(id.toString());
        if (sons > 0) {
            throw new BusinessException("请先删除当前菜单的子菜单！", HttpStatusConstant.FAIL);
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id.toString());
        sysMenu.setMenuCode(sysMenu.getId() + "_" + sysMenu.getMenuCode());
        sysMenu.setIsDeleted(CommonConstant.IS_FALSE);
        return super.updateById(sysMenu);
        // TODO 是否删除菜单按钮关系、权限关系
    }

    @Override
    public List<SysMenu> selectListAll(ParamQuery query) {
        String[] fields = {"id", "parent_id", "menu_title", "menu_type", "menu_path", "order_num", "menu_code"};
        List<SysMenu> dataList = super.selectFieldListAll(query, fields);
        return BeanUtil.isNotEmpty(dataList) ? dataList : new ArrayList<>();
    }

    /**
     * 获取全部菜单树
     * <p>
     * 获取系统全部菜单数据，排除已删除的
     * </p>
     */
    public List<SysMenuTreeVO> getSysMenuTree() {
        // 获取全部数据
        List<SysMenu> menuList = this.selectListAll(null);
        return this.getSysMenuTree(menuList);
    }

    /**
     * 通过菜单列表构建菜单树
     *
     * @param menuList 菜单列表
     */
    public List<SysMenuTreeVO> getSysMenuTree(List<SysMenu> menuList) {
        // 缓存树型结构数据
        List<SysMenuTreeVO> tree = new ArrayList<>(InitialCapacityConstant.INITIAL_128_NUMBER);
        // 构造数据
        if (BeanUtil.isNotEmpty(menuList)) {
            for (SysMenu sysMenu : menuList) {
                // 根节点展开
                tree.add(new SysMenuTreeVO(sysMenu));
            }
        }
        List<SysMenuTreeVO> result = TreeUtil.buildByRecursive(tree, CommonConstant.ROOT);
        return BeanUtil.isEmpty(result) ? new ArrayList<>() : result;
    }

    /**
     * 通过菜单编码获取菜单树
     *
     * @param menuCode 菜单编码
     */
    public int getCountByMenuCode(String menuCode) {
        if (StringUtils.isBlank(menuCode)) {
            return 0;
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuCode(menuCode);
        sysMenu.setIsDeleted(CommonConstant.IS_TRUE);
        return super.selectCount(sysMenu);
    }

    /**
     * 通过父级菜单id，统计子集个数
     *
     * @param parentId 父级菜单id
     */
    public int getSonCountByMenuId(String parentId) {
        if (StringUtils.isBlank(parentId)) {
            return 0;
        }
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(parentId);
        sysMenu.setIsDeleted(CommonConstant.IS_TRUE);
        return this.selectCount(sysMenu);
    }

    /**
     * 通过菜单ids，获取菜单列表
     *
     * @param menuIds 菜单ids
     */
    public List<SysMenu> getMenuListByMenuIds(List<String> menuIds) {
        if (BeanUtil.isEmpty(menuIds)) {
            return new ArrayList<>();
        }
        List<SysMenu> sysMenuList = this.baseMapper.selectMenuListByMenuIds(menuIds);
        return BeanUtil.isEmpty(sysMenuList) ? new ArrayList<>() : sysMenuList;
    }

    /**
     * 获取所有菜单列表
     */
    public List<SysMenu> getAllMenuList() {
        List<SysMenu> sysMenuList = this.baseMapper.selectMenuListByMenuIds(null);
        return BeanUtil.isEmpty(sysMenuList) ? new ArrayList<>() : sysMenuList;
    }

    /**
     * 通过菜单列表构建菜单树
     * <p>
     * 前端动态路由树
     * </p>
     *
     * @param menuList 菜单列表
     */
    public List<MenuTreeDTO> getMenuTreeSysMenuTree(List<SysMenu> menuList) {
        // 缓存树型结构数据
        List<MenuTreeDTO> tree = new ArrayList<>(InitialCapacityConstant.INITIAL_128_NUMBER);
        // 构造数据
        if (BeanUtil.isNotEmpty(menuList)) {
            for (SysMenu sysMenu : menuList) {
                tree.add(new MenuTreeDTO(sysMenu));
            }
        }
        List<MenuTreeDTO> result = TreeUtil.buildByRecursive(tree, CommonConstant.ROOT);
        return BeanUtil.isEmpty(result) ? new ArrayList<>() : result;
    }
}