package com.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "form对象",description = "表单对象form")
public class Form {
    private String formId;
    @ApiModelProperty(value="公示名称",name="publicityName",example="公示名称1")
    private String publicityName;//公示名称
    @ApiModelProperty(value="标段名称",name="bidName",example="多选用逗号拼接，如1或者1,2")
    private String bidName;//标段名称
    @ApiModelProperty(value="公示类型",name="publicityType",example="1或者2")
    private String publicityType;//公示类型
    @ApiModelProperty(value="招标经理",name="bidManager",example="张三")
    private String bidManager;//招标经理
    @ApiModelProperty(value="经理身份证号",name="managerId",example="123456789369852147")
    private String managerId;//经理身份证号
    @ApiModelProperty(value="证书编号",name="certificateId",example="00015")
    private String certificateId;//证书编号
    @ApiModelProperty(value="职业资格",name="titleCertificate",example="证书资格")
    private String titleCertificate;//职业资格
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date bidOpenTime;//开标时间
    @JsonIgnore
    private Date publicityStartTime;//公示开始时间
    @JsonIgnore
    private Date publicityEndTime;//公示结束时间
    @ApiModelProperty(value="委托金额",name="entrustMoney",example="100000")
    private BigDecimal entrustMoney;//委托金额
    @ApiModelProperty(value="公示内容文本",name="managerId",example="1.xxx 2.xxx 3.xxx")
    private String publicityContent;//公示内容文本
//    private String formFile;//附件名
    @ApiModelProperty(value="开标时间",name="openTimeStr",example="yyyy-MM-dd HH:mm:ss")
    private String openTimeStr;
    @ApiModelProperty(value="公示开始时间",name="startTimeStr",example="yyyy-MM-dd HH:mm:ss")
    private String startTimeStr;
    @ApiModelProperty(value="公示结束时间",name="endTimeStr",example="yyyy-MM-dd HH:mm:ss")
    private String endTimeStr;
    //每个表单对应的附件列表
    private List<File> files;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getPublicityName() {
        return publicityName;
    }

    public void setPublicityName(String publicityName) {
        this.publicityName = publicityName;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public String getPublicityType() {
        return publicityType;
    }

    public void setPublicityType(String publicityType) {
        this.publicityType = publicityType;
    }

    public String getBidManager() {
        return bidManager;
    }

    public void setBidManager(String bidManager) {
        this.bidManager = bidManager;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getTitleCertificate() {
        return titleCertificate;
    }

    public void setTitleCertificate(String titleCertificate) {
        this.titleCertificate = titleCertificate;
    }

    public Date getBidOpenTime() {
        return bidOpenTime;
    }

    public void setBidOpenTime(Date bidOpenTime) {
        this.bidOpenTime = bidOpenTime;
    }

    public Date getPublicityStartTime() {
        return publicityStartTime;
    }

    public void setPublicityStartTime(Date publicityStartTime) {
        this.publicityStartTime = publicityStartTime;
    }

    public Date getPublicityEndTime() {
        return publicityEndTime;
    }

    public void setPublicityEndTime(Date publicityEndTime) {
        this.publicityEndTime = publicityEndTime;
    }

    public BigDecimal getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(BigDecimal entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public String getPublicityContent() {
        return publicityContent;
    }

    public void setPublicityContent(String publicityContent) {
        this.publicityContent = publicityContent;
    }

//    public String getFormFile() {
//        return formFile;
//    }
//
//    public void setFormFile(String formFile) {
//        this.formFile = formFile;
//    }

    public String getOpenTimeStr() {
        return openTimeStr;
    }

    public void setOpenTimeStr(String openTimeStr) {
        this.openTimeStr = openTimeStr;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
