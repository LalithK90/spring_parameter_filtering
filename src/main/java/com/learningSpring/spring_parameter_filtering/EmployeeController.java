package com.learningSpring.spring_parameter_filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    List<Employee> employee = new ArrayList<>();

    private void setEmployee(){

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        //employee :- take total value from entity ("This can be done using getOne()")
        //if need to filter List<> need create for lop until list.length

        employee1.id = 1;
        employee1.name = "Lalith";
        employee1.email = "asakahata****@gmail.com";
        employee1.mobile = "07723******";
        employee1.land = "01231****";
        employee1.address = "Colombo 8";

        employee2.id = 2;
        employee2.name = "Lalith1";
        employee2.email = "asakahata*1***@gmail.com";
        employee2.mobile = "07723***1***";
        employee2.land = "01231**1**";
        employee2.address = "Colombo 81";

        employee.add(employee1);
        employee.add(employee2);
    }


        //request few parameter from entity
        @GetMapping("/requestFilterValueFromEntity")
        public MappingJacksonValue filterParameter(){
            setEmployee();

    //Create new mapping jackson value and set it to which was need to filter

            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employee);

            //simpleBeanPropertyFilter :-  need to give any id to addFilter method and created filter which was mentioned what parameter's necessary to provide
      SimpleBeanPropertyFilter simpleBeanPropertyFilter =  SimpleBeanPropertyFilter
                                                .filterOutAllExcept("id","name","email");
            //filters :-  set front end required value to before filter

            FilterProvider filters = new SimpleFilterProvider()
                                .addFilter("Employee", simpleBeanPropertyFilter);
            //Employee :- need to annotate relevant class with JosonFilter  {@JsonFilter("Employee") }
            mappingJacksonValue.setFilters(filters);

            return mappingJacksonValue;
        }
    }




