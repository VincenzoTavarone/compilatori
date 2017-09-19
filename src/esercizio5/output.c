#include <stdio.h>
typedef int bool;
#define TRUE 1
#define FALSE 0
int somma1, somma2, result;
int mul1, mul2;
int div1, div2;
int pow1, pow2;
int fibo;
void fibonacci (){
int f1, f0, fn, i;
f1 = 1;
f0 = 0;
fn = fibo;
i = 2;
while(i<=fibo){
i = i+1;
fn = f1+f0;
f0 = f1;
f1 = fn;
}
printf("%d\n", fn);
}
void multiplication (){
int i, multi;
multi = 0;
i = 0;
while(i<mul2){
multi = multi+mul1;
i = i+1;
}
printf("%d\n", multi);
}
void power (){
int pow, i;
pow = 1;
i = 0;
while(i<pow2){
pow = pow*pow1;
i = i+1;
}
printf("%d\n", pow);
}

int main(){
somma1 = 20;
somma2 = 30;
mul1 = 5;
mul2 = 4;
div1 = 10;
div2 = 5;
pow1 = 3;
pow2 = 4;
fibo = 10;
result = somma1+somma2;
printf("%s\n", "Il risultato della somma è:");
printf("%d\n", result);
printf("%s\n", "Il risultato della moltiplicazione è:");
multiplication();
printf("%s\n", "Il risultato della potenza è:");
power();
printf("%s\n", "Il risultato della divisione è:");
result = div1/div2;
printf("%d\n", result);
printf("%s\n", "Il risultato di fibonacci è:");
fibonacci();

}