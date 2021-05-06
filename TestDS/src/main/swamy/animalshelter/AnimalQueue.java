package main.swamy.animalshelter;

import java.util.LinkedList;

public class AnimalQueue {
	
	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0;//acts as timestamp

	public AnimalQueue() {
		
	}
	
	public void enque(Animal a){
		
		a.setOrder(order);
		order++;
		
		if(a instanceof Dog){
			dogs.addLast((Dog) a);
		}else if(a instanceof Cat){
			cats.addLast((Cat)a);
		}
	}

	
	public Animal dequeAny(){
		if(dogs.size() == 0){
			return dequeDog();
		}else if(cats.size() == 0){
			return dequeCat();
		}
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if(dog.isOlderThan(cat)){
			return dequeDog();
		}else{
			return dequeCat();
		}
	}
	private Animal dequeCat() {
		
		return cats.poll();
	}

	private Animal dequeDog() {
		
		return dogs.poll();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnimalQueue aq = new AnimalQueue();
		aq.enque(new Dog("Dog"));
		aq.enque(new Dog("Dog"));
		aq.enque(new Cat("Cat"));
		aq.enque(new Dog("Dog"));
		aq.enque(new Cat("Cat"));
		Animal a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeAny();
		System.out.println("Animal:"+a);
		a = aq.dequeCat();
		System.out.println("Animal:"+a);
	}

}
