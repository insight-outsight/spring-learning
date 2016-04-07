package org.springlearning.aop.dynamic_data_source;

import java.util.Date;

public class IndexUserPhone {

	private Long id;
	private String mobliePhoneNumber;
	private long uid;
	private int checkStatus;
	private int status;
	private String randCode;
	private int isSelfManage;
	private Date createTime;
	private Date lastModifiedTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobliePhoneNumber() {
		return mobliePhoneNumber;
	}
	public void setMobliePhoneNumber(String mobliePhoneNumber) {
		this.mobliePhoneNumber = mobliePhoneNumber;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRandCode() {
		return randCode;
	}
	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}
	public int getIsSelfManage() {
		return isSelfManage;
	}
	public void setIsSelfManage(int isSelfManage) {
		this.isSelfManage = isSelfManage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	
}
