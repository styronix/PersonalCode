#include <stdio.h>
#include <math.h>

int main(){
    float n, num;
    printf("Enter n\n");
    scanf("%f", &n);
    printf("Enter num\n");
    scanf("%f", &num);
    float result = pow(num, 1/n);
    printf("nth root : %f", result);
    return 0;
}