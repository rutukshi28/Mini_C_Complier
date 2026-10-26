#include <stdio.h>

int main() {
    int a = 10;
    int b = 20;
    int x = ((a + b) * 2);
    printf("%d\n", x);
    if ((x >= 50)) {
        printf("%d\n", x);
    } else {
        printf("%d\n", 0);
    }
    int i = 1;
    while ((i <= 5)) {
        printf("%d\n", i);
        i = (i + 1);
    }
    return 0;
}
