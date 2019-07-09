package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.DevelopingPartyContactPersonDao;
import com.hlh.GLPT.dm.domain.DevelopingPartyContactPerson;
import com.hlh.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  16-5-13  下午5:01
 */
@Service
public class DevelopingPartyContactPersonService {
    @Autowired
    private DevelopingPartyContactPersonDao dPartyContactPersonDao;

    /**
     * 添加
     *
     * @param dPartyContactPerson
     * @return
     */
    public String add(DevelopingPartyContactPerson dPartyContactPerson) {
        return dPartyContactPersonDao.add(dPartyContactPerson);
    }

    /**
     * 批量添加
     *
     * @param dPartyContactPersonList
     * @return
     */
    public String BathAdd(List<DevelopingPartyContactPerson> dPartyContactPersonList) {
        return dPartyContactPersonDao.BathAdd(dPartyContactPersonList);
    }

    /**
     * 彻底删除
     *
     * @param Id
     * @return
     */
    public String ThoroughDel(int Id) {
        return dPartyContactPersonDao.ThoroughDel(Id);
    }

    /**
     * 彻底删除
     *
     * @param KFFCode
     * @return
     */
    public String ThoroughDelEx(String KFFCode) {
        return dPartyContactPersonDao.ThoroughDelEx(KFFCode);
    }

    /**
     * 按ID查询单条记录
     *
     * @param Id
     * @return
     */
    public DevelopingPartyContactPerson findById(int Id) {
        return dPartyContactPersonDao.findById(Id);
    }

    /**
     * 按KFFCode查询单条记录
     *
     * @param KFFCode
     * @return
     */
    public List<DevelopingPartyContactPerson> findByCode(String KFFCode) {
        return dPartyContactPersonDao.findByCode(KFFCode);
    }

    /**
     * 按KFFCode查询多条记录
     *
     * @param KFFCode
     * @return
     */
    public List findByList(String KFFCode) {
        return dPartyContactPersonDao.findByList(KFFCode);
    }

}
