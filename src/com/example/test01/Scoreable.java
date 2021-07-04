package com.example.test01;

//함수적 인터페이스를 작성할 때 두 개이상의 추상 메서드가 선언되지 않도록 확인 하는 기능
@FunctionalInterface
public interface Scoreable {
	int getScore();
}