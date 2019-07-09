package com.hlh.GLPT.dm.service;

import com.hlh.GLPT.dm.dao.IPv4GroupDao;
import com.hlh.GLPT.dm.domain.IPv4Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.hlh.GLPT.dm.service
 * User: 黄良辉  15-12-3  下午7:38
 */
@Service
public class IPv4GroupService {
    @Autowired
    private IPv4GroupDao iPv4GroupDao;

    /*添加*/
    public String add(IPv4Group _IPv4Group) {
        return iPv4GroupDao.add(_IPv4Group);
    }

    /*验证添加*/
    public String VerifyAdd(IPv4Group _IPv4Group) {
         return iPv4GroupDao.VerifyAdd(_IPv4Group);
    }


    /*标识删除*/
    public String del(int Id,boolean DeleteMark) {
         return iPv4GroupDao.del(Id, DeleteMark);
    }

    /*彻底删除*/
    public String ThoroughDel(int Id) {
         return iPv4GroupDao.ThoroughDel(Id);
    }

    /*修改*/
    public String edit(IPv4Group _IPv4Group) {
          return iPv4GroupDao.edit(_IPv4Group);
    }

    /*验证修改*/
    public String VerifyEdit(IPv4Group _IPv4Group) {
          return iPv4GroupDao.VerifyEdit(_IPv4Group);
    }

    /*查询第一条记录*/
    public IPv4Group findByTop1() {
        return iPv4GroupDao.findByTop1();
    }
    /*查询单条记录*/
    public IPv4Group findById(int Id) {
          return iPv4GroupDao.findById(Id);
    }

    /*获取总行数*/
    public int GetRowCount() {
        return iPv4GroupDao.GetRowCount();
    }

    /*获取总行数*/
    public int GetRowCount(boolean Enabled, boolean DeleteMark) {
          return iPv4GroupDao.GetRowCount(Enabled, DeleteMark);
    }

    /*查询所有记录*/
    public List<IPv4Group> findAll() {
        return iPv4GroupDao.findAll();
    }


    /*查询所有记录*/
    public List<IPv4Group> findAllEx(boolean Enabled, boolean DeleteMark) {
          return iPv4GroupDao.findAllEx(Enabled, DeleteMark);
    }

    /*查询所有记录*/
    public String findAllEx(String[] fieldName, boolean Enabled, boolean DeleteMark) {
        return iPv4GroupDao.findAllEx(fieldName, Enabled, DeleteMark);
    }

    /*字段值转换*/
    public String FieldFValToFieldFVal(String pdFieldName, String pdFieldVal, String mbFieldName) {
       return iPv4GroupDao.FieldFValToFieldFVal(pdFieldName, pdFieldVal, mbFieldName);
    }

    /*GroupCode是否重复*/
    public boolean GroupCodeIsRepeat(String GroupCode) {
         return iPv4GroupDao.GroupCodeIsRepeat(GroupCode);
    }

    /*GroupCode是否重复*/
    public boolean GroupCodeIsRepeat(String GroupCode, int Id) {
        return iPv4GroupDao.GroupCodeIsRepeat(GroupCode, Id);
    }
}
