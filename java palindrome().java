class Main
{
  public static void main (String[]args)
  {
    System.out.println ("enter x and y");
    Scanner ob = new Scanner (System.in);
    int x, y, z, r, i, n;
      x = ob.nextInt ();
      y = ob.nextInt ();
      System.out.print ("the palindrome nos are ");
    if (x < y && x > 999 && x < 100000 && y > 999 && y < 10000)
      {
	for (i = x; i <= y; i++)
	  {
	    z = i;
	    n = 0;
	    while (z != 0)
	      {
		r = z % 10;
		z /= 10;
		n = n * 10 + r;
	      }

	    if (n == i)
	      {
		System.out.print (i + " ");
	      }
	  }
      }

  }
}
