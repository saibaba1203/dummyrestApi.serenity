package com.restapiexample.dummy.restapiinfo;

import com.restapiexample.dummy.testbase.TestBase;
import com.restapiexample.dummy.userinfo.EmployeeSteps;
import com.restapiexample.dummy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class RestAPICURDTestWithSteps extends TestBase {
    static String name = "bhumi" + TestUtils.getRandomValue();
    static int salary = 50000 ;
    static int age = 30 ;
    static int id = 21;



    static int employeeID;

    @Steps
    EmployeeSteps employeeSteps;

    @Title("This will create a new employee")
    @Test
    public void test001() {
        HashMap<Object, Object> employeeMap = new HashMap<>();
        employeeMap.put("storeId", 1);
        employeeMap.put("serviceId", 2);

        ValidatableResponse response = employeeSteps.createEmployee( name,salary, age, id);
        response.log().all().statusCode(201);
        employeeSteps = response.log().all().extract().path("id");
        System.out.println(employeeID);
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        HashMap<Object, Object> employeevarrify = employeeSteps.getEmployeeInfoByID(employeeID);
        Assert.assertThat(employeevarrify, hasValue(employeeID));
        System.out.println(employeevarrify);
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";
        HashMap<Object, Object> employeeMap = new HashMap<>();
        employeeMap.put("storeId", 4);
        employeeMap.put("serviceId", 3);

        employeeSteps.updateEmployee(employeeID,name,salary,age,id)
                .log().all().statusCode(200);


        HashMap<Object, Object> employeeupdatemap = employeeSteps.getEmployeeInfoByID(employeeID);
        Assert.assertThat(employeeupdatemap, hasValue(name));

    }
    @Title("Delete the employee and verify if the employee is deleted!")
    @Test
    public void test004() {
        employeeSteps.deletEmployee(employeeID).statusCode(200);
        employeeSteps.getemployeetById(employeeID).statusCode(404);
    }

}
