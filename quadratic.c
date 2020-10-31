#include <stdio.h>
#include <math.h>
int main(){
    int a,b,c;
    printf("Enter a, b, c");
    scanf("%d %d %d", &a,&b,&c);
    float d = (b*b) - 4.0*a*c;
    if(d > 0){
        printf("Roots : %f %f", (-b + sqrt(d))/(2*a), (-b + sqrt(d))/(2*a));
    }
    else if(d == 0){
        printf("Root : %f", (-b + sqrt(d))/(2*a));
    }
    else{
        printf("No real root");
    }
}