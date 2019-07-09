package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.RenewRecordDao;
import com.hlh.GLPT.dm.domain.RenewRecord;
import com.hlh.common.domain.DataPage;
import com.hlh.util.StrUtil;
import com.hlh.util.jsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  16-1-12  上午11:34
 */
@Service
public class RenewRecordService {
    @Autowired
    private RenewRecordDao renewRecordDao;

    /**
     * 添加
     *
     * @param rRecord
     * @return
     */
    public String add(RenewRecord rRecord) {
        return renewRecordDao.add(rRecord);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return renewRecordDao.ThoroughDel(Id);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public RenewRecord findById(int Id) {
        return renewRecordDao.findById(Id);
    }

    /**
     * 查询记录 分页
     *
     * @param fField 查询字段
     * @param fValue 查询值
     * @param start  开始记录
     * @param number 记录数
     * @return
     */
    public String findByPage(String DomainNameId, String RenewDate, String RenewCode, String RenewPeopleType, String fField, String fValue, int start, int number) {
        return renewRecordDao.findByPage(DomainNameId, RenewDate, RenewCode, RenewPeopleType, fField, fValue, start, number);
    }

    /**
     * 查询记录数
     *
     * @param DomainNameId 有效性
     * @param RenewDate    删除标识
     * @param RenewCode    调用标识
     * @param fField       查询字段
     * @param fValue       查询值
     * @return
     */
    public int GetRowCount(String DomainNameId, String RenewDate, String RenewCode, String RenewPeopleType, String fField, String fValue) {
        return renewRecordDao.GetRowCount(DomainNameId, RenewDate, RenewCode, RenewPeopleType, fField, fValue);
    }

    /**
     * 验证添加
     *
     * @param rRecord
     * @return
     */
    public String VerifyAdd(RenewRecord rRecord) {
        return renewRecordDao.VerifyAdd(rRecord);
    }

    /**
     * 验证修改
     *
     * @param rRecord
     * @return
     */
    public String VerifyEdit(RenewRecord rRecord) {
        return renewRecordDao.VerifyEdit(rRecord);
    }

}
