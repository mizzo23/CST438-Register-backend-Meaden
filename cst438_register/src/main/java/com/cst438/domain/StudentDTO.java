package com.cst438.domain;


//StudentDTO class based off of EnrollmentDTO
public class StudentDTO {
	
	public int student_id;
	public String name;
	public String email;
	public int statusCode;
	public String status;
	
	public StudentDTO() {
		this.student_id = 0;
		this.email=null;
		this.name=null;
		this.statusCode = 0;
		this.status=null;
	}
	
	public StudentDTO(int student_id, String name, String email, String status, int statusCode) {
		this.student_id = student_id;
		this.name=name;
		this.email=email;
		this.statusCode = statusCode;
		this.status=status;
	}
	
	public StudentDTO(String email, String name, int statusCode, String status) {
		this.student_id = 0;
		this.name=name;
		this.email=email;
		this.statusCode = statusCode;
		this.status=status;
	}


	@Override
	public String toString() {
		return "StudentDTO [id=" + student_id + ", studentEmail=" + email + ", studentName=" + name
				+ ", statusCode=" + statusCode + ", status=" + status+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDTO other = (StudentDTO) obj;
		if (statusCode != other.statusCode)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
}