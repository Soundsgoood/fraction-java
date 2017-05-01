/**
 * The FractionGuan class represents numerical fractions and allows basic
 * arithmatic operations to be performed with them.
 * @author Orion Guan
 * @version October 3rd, 2016
 */
public class FractionGuan
{
  private int numerator;
  private int denominator;
  private char sign;

  public final static char POSITIVE_SIGN = '+';
  public final static char NEGATIVE_SIGN = '-';
  
  /**
   * Default constructor. Creates a new fraction with default values.
   */
  public FractionGuan()
  {
    numerator = 1;
    denominator = 1;
    sign = POSITIVE_SIGN;
  }

  /**
   * Creates a new fraction given a numerator and denominator. Creates fraction
   * 1/1 if denominator is 0.
   * @param numerator The numerator of the fraction.
   * @param denominator The denominator of the fraction.
   */
  public FractionGuan(int numerator, int denominator)
  {
    if (denominator == 0)
    {
      System.out.print("0 used as denominator. Fraction 1/1 returned.");
      this.sign = POSITIVE_SIGN;
      this.numerator = 1;
      this.denominator = 1;
    }
    else
    {
      this.sign = (numerator * denominator < 0) ? NEGATIVE_SIGN : POSITIVE_SIGN;
      this.numerator = (numerator < 0) ? (-1) * numerator : numerator;
      this.denominator = (denominator < 0) ? (-1) * denominator : denominator;
      simplify();
    }
  }
  
    /**
   * Creates a new fraction given a numerator, denominator, and sign. Creates
   * fraction 1/1 if denominator is 0.
   * @param numerator The numerator of the fraction.
   * @param denominator The denominator of the fraction.
   * @param sign The sign of the fraction, '+' for positive, '-' for negative.
   */
  public FractionGuan(int numerator, int denominator, char sign)
  {
    if (denominator == 0)
    {
      System.out.print("0 used as denominator. Fraction 1/1 set.");
      this.sign = POSITIVE_SIGN;
      this.numerator = 1;
      this.denominator = 1;
    }
    else
    {
      this.sign = ((numerator * denominator < 0) ^ sign == NEGATIVE_SIGN)
        ? NEGATIVE_SIGN : POSITIVE_SIGN;
      this.numerator = (numerator < 0) ? (-1) * numerator : numerator;
      this.denominator = (denominator < 0) ? (-1) * denominator : denominator;
      simplify();
    }
  }

  /**
   * Creates a new fraction representing the given whole number.
   * @param wholeNumber A whole number for the fraction to represent.
   */
  public FractionGuan(int wholeNumber)
  {
    numerator = (wholeNumber >= 0) ? wholeNumber : (-1) * wholeNumber;
    denominator = 1;
    sign = (wholeNumber >= 0) ? POSITIVE_SIGN : NEGATIVE_SIGN;
  }

  /**
   * Creates a new fraction equal to the given fraction parameter.
   * @param rhs The fraction parameter to be copied.
   */
  public FractionGuan(FractionGuan rhs)
  {
    numerator = rhs.numerator;
    denominator = rhs.denominator;
    sign = rhs.sign;
  }

  /**
   * Reduces the fraction to the equivalent simplest form.
   */
  private void simplify()
  {
    int gcf = computeGCF(numerator, denominator);
    numerator /= gcf;
    denominator /= gcf;
  }
  
  /**
   * Generates the positive greatest common factor of two values.
   * @param a The first value.
   * @param b The second value.
   * @return The greatest common factor of a and b.
   */
  private int computeGCF(int a, int b)
  {
    if ((a * b) == 0)
    {
      return 1;
    }
    
    a = (a >= 0) ? a : (-1) * a;
    b = (b >= 0) ? b : (-1) * b;
    int min = (a >= b) ? b : a;
    int gcd = 1;
    for (int i = 1; i <= min; i++)
    {
      if (a % i == 0 && b % i == 0)
      {
        gcd = i;
      }
    }
    return gcd;
  }

  /**
   * Gives whether or not a fraction is equal to another.
   * @param rhs The fraction to compare.
   * @return Whether or not the two fractions are equivalent.
   */
  public boolean equals(FractionGuan rhs)
  {
    return ((numerator == rhs.numerator) && (denominator == rhs.denominator)
      && (sign == rhs.sign));
  }

  /**
   * Gives whether or not a fraction is strictly less than another.
   * @param rhs The fraction that your fraction object might be less than.
   * @return Whether or not your fraction object is less than the parameter
   * fraction.
   */
  public boolean isLessThan(FractionGuan rhs)
  {
    boolean isLessThanRhs = false;
    if ((sign == POSITIVE_SIGN) ^ (rhs.sign == POSITIVE_SIGN))
    {
      isLessThanRhs = (sign == NEGATIVE_SIGN);
    }
    else
    {
      boolean absValLessThanRhs
        = ((numerator * rhs.denominator) < (denominator * rhs.numerator));
      boolean absValMoreThanRhs
        = ((numerator * rhs.denominator) > (denominator * rhs.numerator));
      isLessThanRhs = (sign == POSITIVE_SIGN)
        ? absValLessThanRhs : absValMoreThanRhs;
    }
    return isLessThanRhs;
  }

  /**
   * Adds this Fraction with the parameter Fraction.
   * @param rhs The Fraction to be added to the object Fraction.
   * @return The sum.
   */
  public FractionGuan add(FractionGuan rhs)
  {
    int partialNumerator1 = (sign == NEGATIVE_SIGN)
      ? (-1) * numerator * rhs.denominator : numerator * rhs.denominator;
    int partialNumerator2 = (rhs.sign == NEGATIVE_SIGN)
      ? (-1) * rhs.numerator * denominator : rhs.numerator * denominator;
    int resultNumerator = partialNumerator1 + partialNumerator2;
    int resultDenominator = denominator * rhs.denominator;
    return new FractionGuan(resultNumerator, resultDenominator);
  }

  /**
   * Subtracts from this Fraction by the parameter Fraction.
   * @param rhs The Fraction to be subtracted from the object Fraction.
   * @return The difference.
   */
  public FractionGuan subtract(FractionGuan rhs)
  {
    int partialNumerator1 = (sign == NEGATIVE_SIGN)
      ? (-1) * numerator * rhs.denominator : numerator * rhs.denominator;
    int partialNumerator2 = (rhs.sign == NEGATIVE_SIGN)
      ? (-1) * rhs.numerator * denominator : rhs.numerator * denominator;
    int resultNumerator = partialNumerator1 - partialNumerator2;
    int resultDenominator = denominator * rhs.denominator;
    return new FractionGuan(resultNumerator, resultDenominator);
  }
  
  /**
   * Multiplies this Fraction by the parameter Fraction.
   * @param rhs the right-hand side factor
   * @return the product
   */
  public FractionGuan multiply(FractionGuan rhs)
  {
    int resultNumerator = numerator * rhs.numerator;
    int resultDenominator = denominator * rhs.denominator;
    if (sign != rhs.sign)
    {
      resultNumerator *= (-1);
    }
    return new FractionGuan(resultNumerator, resultDenominator);
  }

  /**
   * Divides this fraction by the parameter fraction.
   * @param rhs The right-hand side factor.
   * @return The quotient.
   */
  public FractionGuan divide(FractionGuan rhs)
  {
    if (rhs.numerator == 0)
    {
      System.out.println("The divide() method has been used to divide a fraction by 0. No division occured.");
      rhs.numerator = 1;
      rhs.denominator = 1;
      rhs.sign = POSITIVE_SIGN;
    }
    int resultNumerator = numerator * rhs.denominator;
    int resultDenominator = denominator * rhs.numerator;
    if (sign != rhs.sign)
    {
      resultNumerator *= (-1);
    }
    return new FractionGuan(resultNumerator, resultDenominator);
  }

  /**
   * Provides a string representation of a fraction. A sign is
   * provided only for negative values.
   * @return The string representation of the fraction.
   */
  public String toString()
  {
    String signPrefix = (sign == NEGATIVE_SIGN) ? "-" : "";
    return signPrefix + numerator + "/" + denominator;
  }
  
  /**
   * Converts a Fraction to its equivalent decimal value.
   * @return The decimal representation.
   */
  public double toDecimal()
  {
    double result = (double)(numerator) / denominator;
    result = sign == NEGATIVE_SIGN ? result * -1.0 : result;
    return result;
  }
}