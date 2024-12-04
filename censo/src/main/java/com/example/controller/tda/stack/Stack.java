package com.example.controller.tda.stack;

public class Stack <E>{
    private StackOPeration<E> stackOperation;

    public Stack(Integer top){
        this.stackOperation = new StackOPeration<>(top);
    }   
    
    public void push(E dato) throws Exception{
        stackOperation.push(dato);
    }

    public Integer getSize(){
        return this.stackOperation.getSize();
    }

    public void clear(){
        this.stackOperation.reset();
    }

    public Integer getTop(){
        return this.stackOperation.getTop();
    }

    public void print (){
        System.out.println("Pila: ");
        System.out.println(stackOperation.toString());
        System.out.println("**********");

    }

    public E pop() throws  Exception{
        return stackOperation.pop();
    } 

}
