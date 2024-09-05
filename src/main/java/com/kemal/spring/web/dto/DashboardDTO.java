package com.kemal.spring.web.dto;

public class DashboardDTO {

	private Integer total = 0;

	private Integer closed = 0;

	private Integer pending = 0;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getClosed() {
		return closed;
	}

	public void setClosed(Integer closed) {
		this.closed = closed;
	}

	public Integer getPending() {
		return pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

}
