package com.mrntlu.notificationexample;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class LambdaExpressionExamples extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(this::toastMessage, //Method Reference
                2000);

        new Handler().postDelayed(() -> //Lambda Reference
                toastMessage(),
                2000);

        new Handler().postDelayed(new Runnable() { //Normal way
            @Override
            public void run() {
                toastMessage();
            }
        },2000);



        findViewById(R.id.button4).setOnClickListener(view -> { //Lambda Reference
            toastMessage();
        });

        findViewById(R.id.button4).setOnClickListener(this::toastMessageWithView); //Method Reference with same parameters



        ToastInterface toastInterface= (email, password) -> toastMessage(); //Functional Interface with Lambda Expressions

        findViewById(R.id.button).setOnClickListener(view -> { //Lambda
            toastInterface.createToast("tester","test123");
        });



        BiConsumer<String,String> biConsumer=(email,password)->{ //BiConsumer represents an operation that accepts two input arguments and returns no result.
            Toast.makeText(this, email+" " +password, Toast.LENGTH_SHORT).show();
        };
        biConsumer.accept("tester","password");



        BiFunction<Integer,Integer,String> biFunction=(num1,num2)->"Result:"+(num1+num2); //BiFunction represents a function that accepts two arguments and produces a result.
        System.out.println(biFunction.apply(20,25)); //Applies this function to the given arguments.



        BiPredicate<Integer,String> condition1=(i,s)-> i>20 && s.startsWith("R"); //BiPredicate represents a predicate which is a boolean-valued function of two arguments.
        BiPredicate<Integer,String> condition2=(i,s)-> i>25 && s.startsWith("P");
        System.out.println(condition1.test(10,"Recep")); //BiPredicate test method evaluates this predicate on the given arguments.
        System.out.println(condition1.and(condition2).test(10,"Pinar")); //BiPredicate and method returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
        System.out.println(condition1.or(condition2).test(10,"Pinar")); //BiPredicate or returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.

    }

    public void toastMessage(){
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
    }

    public void toastMessageWithView(View view) {
        toastMessage();
    }
}

@FunctionalInterface
interface ToastInterface{
    abstract void createToast(String email,String password);
}