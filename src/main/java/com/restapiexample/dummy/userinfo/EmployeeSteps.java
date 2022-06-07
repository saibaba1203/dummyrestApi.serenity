package com.restapiexample.dummy.userinfo;

import com.restapiexample.dummy.constants.EndPoints;
import com.restapiexample.dummy.model.EmployeePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class EmployeeSteps {
    @Step("Creating employee with name : {0}, salary: {1}, age: {2}, id: {3} ")
    public ValidatableResponse createEmployee(String name, int salary, int age,
                                              int id ) {
        EmployeePojo employeePojo= new EmployeePojo();
        employeePojo.setName(name);
        employeePojo.setSalary(salary);
        employeePojo.setAge(age);
        employeePojo.setId(id);



        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(employeePojo)
                .when()
                .post(EndPoints.CREATE_SINGLE_EMPLOYEE)
                .then();

    }

    @Step("Getting the Employee information with name: {0}")
    public HashMap<Object, Object> getEmployeeInfoByID(int employeeID) {

        HashMap<Object, Object> employeeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("employeeID",employeeID)
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");

        return employeeMap;
    }

    @Step("Updating Employee information with employeeId:  {0}, name: {1}, salary: {2}, age: {3} id : {4}")
    public ValidatableResponse updateEmployee(int employeeId, String name, int salary, int age,int id){

        EmployeePojo employeePojo= new EmployeePojo();
        employeePojo.setName(name);
        employeePojo.setSalary(salary);
        employeePojo.setAge(age);
        employeePojo.setId(id);





        return SerenityRest.given().log().all()
                .pathParam("employeeId",employeeId)
                .contentType(ContentType.JSON)
                .body(employeePojo)
                .when()
                .put(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then();

    }

    @Step("Deleting employee information with userID: {0}")
    public ValidatableResponse deletEmployee(int employeeId){
        return SerenityRest.given().log().all()
                .pathParam("employeeId", employeeId)
                .when()
                .delete(EndPoints.DELETE_EMPLOYEE_BY_ID)
                .then();
    }

    @Step("Getting Employee information with employeeId: {0}")
    public ValidatableResponse getemployeetById(int employeeId){
        return SerenityRest.given().log().all()
                .pathParam("employeeId", employeeId)
                .when()
                .get(EndPoints.GET_SINGLE_EMPLOYEE_BY_ID)
                .then();
    }

}
