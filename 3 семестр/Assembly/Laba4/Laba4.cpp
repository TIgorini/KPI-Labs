#include <stdio.h>
#define n 3					// ������� ����� � ����������� ����
typedef unsigned char byte;			// ��� ������ � ������� ��������������� ��� char

extern "C"
{
	void BigShowN(byte* p1, int p2);
	bool Biggr(byte *M1, byte *M2, short len);
}

int main()
{
	byte x[n], y[n];					//�������� �����
	for (int i = 0; i<n; i++)
	{
		y[i] = i;
		x[i] = n - i;
	}
	
	printf("x:");
	BigShowN(x, n);
	printf("y:");
	BigShowN(y, n); 
	if (Biggr(x, y, n))
		printf("x > y: true\n");
	else
		printf("x > y: false\n");
	return 0;
}