package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tws.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll(@Param("keyword") String keyword,@Param("offset") int offset,@Param("count") int count);
    
    Employee selectOne(@Param("id") String id);
    
    public void insert(@Param("employee") Employee employee);
    public void update(@Param("id") String id,@Param("employee") Employee employee);
    public void deleteOne(@Param("id") String id);
    
    Employee selectdto(@Param("id") String id);
    
}

