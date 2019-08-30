package tws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tws.dto.EmployeeDto;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public EmployeeDto getById(String id) {
		Employee employee = employeeMapper.selectOne(id);
    	EmployeeDto employeeDto = new EmployeeDto();
    	employeeDto.setId(employee.getId());
    	employeeDto.setName(employee.getName());
    	employeeDto.setAge(employee.getAge());
    	String desc = String.format("name:%s,name:%s",employee.getName(),employee.getAge());
    	employeeDto.setDesc(desc);
    	return employeeDto;
	}

	public List<Employee> selectAll(String keyword,int page, int pageSize) {
		int offset = (page-1)*(pageSize);
		int count = pageSize;
		List<Employee> employees = employeeMapper.selectAll(keyword,offset,count);
		return employees;
	}
	
	
}
