package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.ItemsDao;
import com.hlh.GLPT.core.domain.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * User: 黄良辉
 * Date: 14-3-12
 * Time: 上午10:40
 */
@Service
public class ItemsService {
    @Autowired
    private ItemsDao itemsDao;

    /*
    * 增加
    * */
    public String add(Items items) {
        return itemsDao.add(items);
    }

    /*
    * 彻底删除
    * */
    public String  ThoroughDel(String ItemsId) {
        return itemsDao.ThoroughDel(ItemsId);
    }

    /*
    * 删除
    * */
    public String del(String ItemsId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return itemsDao.del(ItemsId, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(Items items) {
        return itemsDao.edit(items);
    }

    /*
    * 按Id查询
    * */
    public Items findById(String ItemsId) {
        return itemsDao.findById(ItemsId);
    }

    /*
   * 按数据字典主表的名称返回数据字典主表主键
   * */
    public String findByFullName(String fullName){
        return itemsDao.findByFullName(fullName);
    }

    /*
  * 按数据字典主表的代码返回数据字典主表主键
  * */
    public String findByCode(String Code) {
        return itemsDao.findByCode(Code);
    }

    //获取总行数
    public int GetRowCount() {
        return itemsDao.GetRowCount();
    }

    //查询所有数据并返回简单树结构
    public String FindAllTreeSimpleData() {
        return itemsDao.findAllSimpleTreeData();
    }

    //验证添加
    public String VerifyAdd(Items items) {
        return itemsDao.VerifyAdd(items);
    }

    //验证修改
    public String VerifyEdit(Items items) {
        return itemsDao.VerifyEdit(items);
    }

}
