/*!
* file: Employee.h
* written: 16/04/2017
* synopsis: Employee class declaration
* author: Igor Tymoshenko
*/

#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include <string>
#include <iostream>
#include <stdio.h>
#include <string.h>

using namespace std;

//An abstract class
class Person{
public:
	Person(){};
	Person(string _f_name, string _l_name, int _age) : age(_age),
 		f_name(_f_name), l_name(_l_name){};
	virtual void display() = 0;

protected:
	string f_name;
	string l_name;
	int age;
};

class Employee : public Person{
public:
	Employee(){};
	Employee(string _f_name, string _l_name, int _age, int _id) : 
		Person(_f_name, _l_name, _age), id(_id){};
	Employee(const Employee &e);
	Employee& operator=(const Employee &e);
	void setSalary(int s);
	void setDepartment(string dept);
	void setId(int n);
	int getId();
	string getDepartment();
	string getFirstName();
	string getLastName();
	int getAge();
	int getSalary();
	virtual void display();

protected:
	string department;
	int salary;
	int id;	
};