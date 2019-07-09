package com.hlh.GLPT.dm.domain;

/**
 * Package: com.hlh.GLPT.dm.domain
 * User: 黄良辉  16-4-29  下午2:01
 */
public class DevelopingPartyContactPerson {
    private int Id;
    private String KFFCode;
    private String ContactPersonRoleCode;
    private String ContactPersonFullName;
    private String ContactMethodCode;
    private String ContactMethodContent;

    public DevelopingPartyContactPerson() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getKFFCode() {
        return KFFCode;
    }

    public void setKFFCode(String KFFCode) {
        this.KFFCode = KFFCode;
    }

    public String getContactPersonRoleCode() {
        return ContactPersonRoleCode;
    }

    public void setContactPersonRoleCode(String contactPersonRoleCode) {
        ContactPersonRoleCode = contactPersonRoleCode;
    }

    public String getContactPersonFullName() {
        return ContactPersonFullName;
    }

    public void setContactPersonFullName(String contactPersonFullName) {
        ContactPersonFullName = contactPersonFullName;
    }

    public String getContactMethodCode() {
        return ContactMethodCode;
    }

    public void setContactMethodCode(String contactMethodCode) {
        ContactMethodCode = contactMethodCode;
    }

    public String getContactMethodContent() {
        return ContactMethodContent;
    }

    public void setContactMethodContent(String contactMethodContent) {
        ContactMethodContent = contactMethodContent;
    }
}
