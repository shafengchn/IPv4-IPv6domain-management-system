package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.SignRecordDao;
import com.hlh.GLPT.dm.domain.SignRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  16-1-10  下午8:32
 */
@Service
public class SignRecordService {
    @Autowired
    private SignRecordDao signRecordDao;

    /**
     * 添加
     *
     * @param sRecord
     * @return
     */
    public String add(SignRecord sRecord) {
        return signRecordDao.add(sRecord);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return signRecordDao.ThoroughDel(Id);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public SignRecord findById(int Id) {
        return signRecordDao.findById(Id);
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
    public String findByPage(String DomainNameId, String SignDate, String SignCode,String SignPeopleType, String fField, String fValue, int start, int number) {
        return signRecordDao.findByPage(DomainNameId, SignDate, SignCode,SignPeopleType, fField, fValue, start, number);
    }

    /**
     * 查询记录数
     *
     * @param DomainNameId 有效性
     * @param SignDate     删除标识
     * @param SignCode     调用标识
     * @param fField       查询字段
     * @param fValue       查询值
     * @return
     */
    public int GetRowCount(String DomainNameId, String SignDate, String SignCode,String SignPeopleType, String fField, String fValue) {
        return signRecordDao.GetRowCount(DomainNameId, SignDate, SignCode,SignPeopleType, fField, fValue);
    }

    /**
     * 验证添加
     *
     * @param sRecord
     * @return
     */
    public String VerifyAdd(SignRecord sRecord) {
        return signRecordDao.VerifyAdd(sRecord);
    }

    /**
     * 验证修改
     *
     * @param sRecord
     * @return
     */
    public String VerifyEdit(SignRecord sRecord) {
        return signRecordDao.VerifyEdit(sRecord);
    }

}
