package com.hlh.GLPT.dm.dao;

import com.hlh.GLPT.dm.domain.DevelopingPartyContactPerson;
import com.hlh.common.service.DbToolsService;
import com.hlh.util.DbUtilsTemplate;
import com.hlh.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.dao
 * User: 黄良辉  15-6-3  下午10:27
 */
@Repository
public class DevelopingPartyContactPersonDao {
    @Autowired
    private DbToolsService dbToolsService;
    @Autowired
    private DbUtilsTemplate dbUtilsTemplate;

    /**
     * 添加
     *
     * @param dPartyContactPerson
     * @return
     */
    public String add(DevelopingPartyContactPerson dPartyContactPerson) {
        String[] fField = new String[]{"KFFCode", "ContactPersonRoleCode", "ContactPersonFullName", "ContactMethodCode", "ContactMethodContent"};
        Object[] fVal = new Object[]{dPartyContactPerson.getKFFCode(), dPartyContactPerson.getContactPersonRoleCode(), dPartyContactPerson.getContactPersonFullName(), dPartyContactPerson.getContactMethodCode(), dPartyContactPerson.getContactMethodContent()};
        return dbToolsService.add("dm_DevelopingPartyContactPerson", fField, fVal);
    }

    /**
     * 批量添加
     *
     * @param dPartyContactPersonList
     * @return
     */
    public String BathAdd(List<DevelopingPartyContactPerson> dPartyContactPersonList) {
        int YesCount = 0;
        for (int i1 = 0; i1 < dPartyContactPersonList.size(); i1++) {
            if ("OK".equals(add(dPartyContactPersonList.get(i1)))) {
                YesCount++;
            }
        }
        if (YesCount == dPartyContactPersonList.size()) {
            return "OK";
        } else {
            return "批量添加成功[" + YesCount + "]个联系人";
        }
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dbToolsService.ThoroughDel("dm_DevelopingPartyContactPerson", "Id", StrUtil.toStr(Id), "int");
    }

    /**
     * 彻底删除
     *
     * @param KFFCode
     * @return
     */
    public String ThoroughDelEx(String KFFCode) {
        return dbToolsService.ThoroughDel("dm_DevelopingPartyContactPerson", "KFFCode", KFFCode, "str");
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public DevelopingPartyContactPerson findById(int Id) {
        String sqlStr = "SELECT * FROM dm_DevelopingPartyContactPerson WHERE Id=?";
        return dbUtilsTemplate.findFirst(DevelopingPartyContactPerson.class, sqlStr, Id);
    }

    /**
     * 按KFFCode查询多条记录
     *
     * @param KFFCode
     * @return
     */
    public List<DevelopingPartyContactPerson> findByCode(String KFFCode) {
        String sqlStr = "SELECT * FROM dm_DevelopingPartyContactPerson WHERE KFFCode=?";
        return dbUtilsTemplate.find(DevelopingPartyContactPerson.class, sqlStr, KFFCode);
    }

    /**
     * 按KFFCode查询多条记录
     * @param KFFCode
     * @return
     */
    public List findByList(String KFFCode) {
        String sqlStr = "SELECT Id,(SELECT FullName FROM core_ItemDetails WHERE `Code`=dm_DevelopingPartyContactPerson.ContactPersonRoleCode LIMIT 1) as ContactPersonRoleName"
                + ",ContactPersonFullName,(SELECT FullName FROM core_ItemDetails WHERE `Code`=dm_DevelopingPartyContactPerson.ContactMethodCode LIMIT 1) as ContactMethodName"
                + ",ContactMethodContent from dm_DevelopingPartyContactPerson WHERE KFFCode=?";
        return dbUtilsTemplate.executeQueryList(sqlStr, KFFCode);
    }

}
