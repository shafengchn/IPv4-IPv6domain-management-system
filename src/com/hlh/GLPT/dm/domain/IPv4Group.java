package com.hlh.GLPT.dm.domain;
import java.io.Serializable;
import java.util.Date;

/**
 * IPv4地址分组
 * User: 黄良辉
 * Date: 2015-12-03
 * Time: 19:13
 */
public class IPv4Group implements Serializable {
    public IPv4Group(){}
    private int Id;
    private String GroupCode;
    private String GroupName;
    private String Remarks;
    private boolean Enabled;
    private int SortCode;
    private boolean DeleteMark;
    private Date CreateDate;
    private String CreateUserCode;
    private String CreateUserName;
    private Date ModifyDate;
    private String ModifyUserCode;
    private String ModifyUserName;
    public int getId(){return Id;}
    public void setId(int _Id){Id=_Id;}
    public String getGroupCode(){return GroupCode;}
    public void setGroupCode(String _GroupCode){GroupCode=_GroupCode;}
    public String getGroupName(){return GroupName;}
    public void setGroupName(String _GroupName){GroupName=_GroupName;}
    public String getRemarks(){return Remarks;}
    public void setRemarks(String _Remarks){Remarks=_Remarks;}
    public boolean isEnabled(){return Enabled;}
    public void setEnabled(boolean _Enabled){Enabled=_Enabled;}
    public int getSortCode(){return SortCode;}
    public void setSortCode(int _SortCode){SortCode=_SortCode;}
    public boolean isDeleteMark(){return DeleteMark;}
    public void setDeleteMark(boolean _DeleteMark){DeleteMark=_DeleteMark;}
    public Date getCreateDate(){return CreateDate;}
    public void setCreateDate(Date _CreateDate){CreateDate=_CreateDate;}
    public String getCreateUserCode(){return CreateUserCode;}
    public void setCreateUserCode(String _CreateUserCode){CreateUserCode=_CreateUserCode;}
    public String getCreateUserName(){return CreateUserName;}
    public void setCreateUserName(String _CreateUserName){CreateUserName=_CreateUserName;}
    public Date getModifyDate(){return ModifyDate;}
    public void setModifyDate(Date _ModifyDate){ModifyDate=_ModifyDate;}
    public String getModifyUserCode(){return ModifyUserCode;}
    public void setModifyUserCode(String _ModifyUserCode){ModifyUserCode=_ModifyUserCode;}
    public String getModifyUserName(){return ModifyUserName;}
    public void setModifyUserName(String _ModifyUserName){ModifyUserName=_ModifyUserName;}
}