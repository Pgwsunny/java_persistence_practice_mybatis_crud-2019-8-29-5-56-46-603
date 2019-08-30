package tws.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import tws.entity.Employee;

@RunWith(SpringRunner.class)
@MybatisTest
public class EmployeeMapperTest {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@After
	public void teardown() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "employee");
	}
	
	@Test
	public void shouldFetchList() {
//		given
		jdbcTemplate.execute("insert into employee values('001','zhangsan',22)");
//		when
		List<Employee> employees = employeeMapper.selectAll("li",1,5);
//		then
		assertEquals(1, employees.size());
		assertEquals("zhang", employees.get(0).getName());
	}
	
	@Test
	public void shouldInsertList() {
//		jdbcTemplate.execute("insert into employee values('001','zhangsan',22)");
		
		employeeMapper.insert(new Employee("002","lisi","24"));
		int numbers = JdbcTestUtils.countRowsInTable(jdbcTemplate, "employee");
		assertEquals(1, numbers);
	}
	
	@Test
	public void shouldPutList() {
		jdbcTemplate.execute("insert into employee values('001','zhangsan',22)");
		
		employeeMapper.update("002",new Employee("002","zhangsan","20"));
		
//		assertEquals(1, employees.size());
	}
	
	@Test
	public void shouldDeleteList() {
		jdbcTemplate.execute("insert into employee values('001','zhangsan',22)");
		
		employeeMapper.insert(new Employee("002","lisi","24"));
		
//		assertEquals(1, employees.size());
	}
}
