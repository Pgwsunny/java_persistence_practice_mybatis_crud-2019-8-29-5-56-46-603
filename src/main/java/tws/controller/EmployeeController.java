package tws.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tws.dto.EmployeeDto;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll(@RequestParam(value = "keyword",required = false) String keyword,
    		@RequestParam int page,
    		@RequestParam int pageSize) {
        return ResponseEntity.ok(employeeService.selectAll(keyword,page,pageSize));
    }
    /**
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable String id){
    	Employee employee = employeeMapper.selectOne(id);
    	return ResponseEntity.ok(employee);
    }
    */
    @PostMapping("")
    public ResponseEntity<Employee> insert(@RequestBody Employee employee){
    	String id= UUID.randomUUID().toString();
    	employee.setId(id);
    	employeeMapper.insert(employee);
    	return ResponseEntity.created(URI.create(id)).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateOne(@PathVariable String id, @RequestBody Employee employee){
    	employeeMapper.update(id,employee);
    	return ResponseEntity.ok(employee);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteOne(@PathVariable String id){
    	employeeMapper.deleteOne(id);
    	return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable String id){
    	EmployeeDto employeeDto = employeeService.getById(id);
    	return ResponseEntity.ok(employeeDto);
    }

}
