#include <stdio.h>

int main(){
    float basic;
    printf("Enter Basic Salary");
    scanf("%f", &basic);
    float da = 10.0/100 * basic;
    float ta = 20.0/100 * basic;
    printf("Gross Salary = %.2f", basic+da+ta);
    return 0;
}