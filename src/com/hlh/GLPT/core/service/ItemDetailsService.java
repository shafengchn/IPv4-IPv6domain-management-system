package com.hlh.GLPT.core.service;

import com.hlh.GLPT.core.dao.ItemDetailsDao;
import com.hlh.GLPT.core.domain.ItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: 黄良辉
 * Date: 14-3-12
 * Time: 上午10:40
 */
@Service
public class ItemDetailsService {
    @Autowired
    private ItemDetailsDao itemDetailsDao;

    /*
    * 增加
    * */
    public String add(ItemDetails itemDetails) {
        return itemDetailsDao.add(itemDetails);
    }

    /*
    * 彻底删除
    * */
    public String  ThoroughDel(String ItemDetailsId) {
        return itemDetailsDao.ThoroughDel(ItemDetailsId);
    }

    /*
    * 删除
    * */
    public String del(String ItemDetailsId, Date ModifyDate, String ModifyUserCode, String ModifyUserName) {
        return itemDetailsDao.del(ItemDetailsId, ModifyDate, ModifyUserCode, ModifyUserName);
    }

    /*
    * 修改
    * */
    public String edit(ItemDetails itemDetails) {
        return itemDetailsDao.edit(itemDetails);
    }

    /*
    * 按Id查询
    * */
    public ItemDetails findById(String ItemDetailsId) {
        return itemDetailsDao.findById(ItemDetailsId);
    }

    /*
* 按Code查询
* */
    public ItemDetails findByCode(String ItemCode) {
        return itemDetailsDao.findByCode(ItemCode);
    }

    /*
  * 按数据字典主表的主键 返回数据字典明细的记录　ItemDetailsId,ItemName
  * */
    public String findByItemDetails(String ItemsId){
        return itemDetailsDao.findByItemDetails(ItemsId);
    }

    /*
* 按数据字典主表的主键 返回数据字典明细的记录　ItemDetailsId,ItemName
* */
    public List<ItemDetails> findListByItemDetails(String ItemsId){
        return  itemDetailsDao.findListByItemDetails(ItemsId);
    }

    //获取总行数
    public int GetRowCount() {
        return itemDetailsDao.GetRowCount();
    }

    public String findAll(String ShowFieldName, String[] strings) {
        return itemDetailsDao.findAll(ShowFieldName,strings);
    }

    //分页
    public String findByPage(String findValue, int start, int number) {
        return itemDetailsDao.findByPage(findValue,start,number);
    }

    //获取总行数(带查询参数)
    public int GetRowCount(String findValue) {
        return itemDetailsDao.GetRowCount(findValue);
    }

    //验证添加
    public String VerifyAdd(ItemDetails itemDetails) {
        return itemDetailsDao.VerifyAdd(itemDetails);
    }

    //验证修改
    public String VerifyEdit(ItemDetails itemDetails) {
        return itemDetailsDao.VerifyEdit(itemDetails);
    }

    //代码转名称
    public String CodeToFullName(String Code) {
        return itemDetailsDao.CodeToFullName(Code);
    }

    //名称转代码
    public String FullNameToCode(String ItemsName, String FullName) {
        return itemDetailsDao.FullNameToCode(ItemsName, FullName);
    }

    /*
* 查询
* */
    public List<ItemDetails> findAllEx(String ItemsId) {
        return itemDetailsDao.findAllEx(ItemsId);
    }

}
