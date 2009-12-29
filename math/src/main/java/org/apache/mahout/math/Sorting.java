/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*

Copyright � 1999 CERN - European Organization for Nuclear Research.
Permission to use, copy, modify, distribute and sell this software and its documentation for any purpose 
is hereby granted without fee, provided that the above copyright notice appear in all copies and 
that both that copyright notice and this permission notice appear in supporting documentation. 
CERN makes no representations about the suitability of this software for any purpose. 
It is provided "as is" without expressed or implied warranty.
*/
package org.apache.mahout.math;

import java.util.Comparator;

import org.apache.mahout.math.function.ByteComparator;
import org.apache.mahout.math.function.CharComparator;
import org.apache.mahout.math.function.DoubleComparator;
import org.apache.mahout.math.function.FloatComparator;
import org.apache.mahout.math.function.IntComparator;
import org.apache.mahout.math.function.LongComparator;
import org.apache.mahout.math.function.ShortComparator;


public final class Sorting {
  
  
  /* Specifies when to switch to insertion sort */
  private static final int SIMPLE_LENGTH = 7;
  
  private Sorting() {
  /* empty */
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code byte} array to search.
   * @param value
   *          the {@code byte} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(byte[] array, byte value, int from,
      int to) {
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (value > array[mid]) {
        from = mid + 1;
      } else if (value == array[mid]) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    
    return -mid - (value < array[mid] ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code char} array to search.
   * @param value
   *          the {@code char} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(char[] array, char value, int from,
      int to) {
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (value > array[mid]) {
        from = mid + 1;
      } else if (value == array[mid]) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    return -mid - (value < array[mid] ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code double} array to search.
   * @param value
   *          the {@code double} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(double[] array, double value, int from,
      int to) {
    long longBits = Double.doubleToLongBits(value);
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (lessThan(array[mid], value)) {
        from = mid + 1;
      } else if (longBits == Double.doubleToLongBits(array[mid])) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    return -mid - (lessThan(value, array[mid]) ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code float} array to search.
   * @param value
   *          the {@code float} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(float[] array, float value, int from,
      int to) {
    int intBits = Float.floatToIntBits(value);
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (lessThan(array[mid], value)) {
        from = mid + 1;
      } else if (intBits == Float.floatToIntBits(array[mid])) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    return -mid - (lessThan(value, array[mid]) ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code int} array to search.
   * @param value
   *          the {@code int} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(int[] array, int value, int from, int to) {
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (value > array[mid]) {
        from = mid + 1;
      } else if (value == array[mid]) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    return -mid - (value < array[mid] ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code long} array to search.
   * @param value
   *          the {@code long} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(long[] array, long value, int from, int to) {
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (value > array[mid]) {
        from = mid + 1;
      } else if (value == array[mid]) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    return -mid - (value < array[mid] ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code Object} array to search.
   * @param object
   *          the {@code Object} element to find
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   * 
   */
  public static <T extends Comparable<T>> int binarySearchFromTo(T[] array,
      T object, int from, int to) {
    if (array.length == 0) {
      return -1;
    }
    
    int mid = 0, result = 0;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if ((result = array[mid].compareTo(object)) < 0) {
        from = mid + 1;
      } else if (result == 0) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    return -mid - (result >= 0 ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array using the {@code Comparator} to compare elements.
   * Searching in an unsorted array has an undefined result. It's also undefined
   * which element is found if there are multiple occurrences of the same
   * element.
   * 
   * @param array
   *          the sorted array to search
   * @param object
   *          the element to find
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @param comparator
   *          the {@code Comparator} used to compare the elements.
   * @return the non-negative index of the element, or a negative index which
   */
  public static <T> int binarySearchFromTo(T[] array, T object, int from,
      int to, Comparator<? super T> comparator) {
    int mid = 0, result = 0;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if ((result = comparator.compare(array[mid], object)) < 0) {
        from = mid + 1;
      } else if (result == 0) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    return -mid - (result >= 0 ? 1 : 2);
  }
  
  /**
   * Performs a binary search for the specified element in the specified
   * ascending sorted array. Searching in an unsorted array has an undefined
   * result. It's also undefined which element is found if there are multiple
   * occurrences of the same element.
   * 
   * @param array
   *          the sorted {@code short} array to search.
   * @param value
   *          the {@code short} element to find.
   * @param from
   *          the first index to sort, inclusive.
   * @param to
   *          the last index to sort, inclusive.
   * @return the non-negative index of the element, or a negative index which is
   *         {@code -index - 1} where the element would be inserted.
   */
  public static int binarySearchFromTo(short[] array, short value, int from, int to) {
    int mid = -1;
    while (from <= to) {
      mid = (from + to) >>> 1;
      if (value > array[mid]) {
        from = mid + 1;
      } else if (value == array[mid]) {
        return mid;
      } else {
        to = mid - 1;
      }
    }
    if (mid < 0) {
      return -1;
    }
    return -mid - (value < array[mid] ? 1 : 2);
  }
  
  private static boolean lessThan(double double1, double double2) {
    // A slightly specialized version of
    // Double.compare(double1, double2) < 0.
    
    // Non-zero and non-NaN checking.
    if (double1 < double2) {
      return true;
    }
    if (double1 > double2) {
      return false;
    }
    if (double1 == double2 && 0.0d != double1) {
      return false;
    }
    
    // NaNs are equal to other NaNs and larger than any other double.
    if (Double.isNaN(double1)) {
      return false;
    } else if (Double.isNaN(double2)) {
      return true;
    }
    
    // Deal with +0.0 and -0.0.
    long d1 = Double.doubleToRawLongBits(double1);
    long d2 = Double.doubleToRawLongBits(double2);
    return d1 < d2;
  }
  
  private static boolean lessThan(float float1, float float2) {
    // A slightly specialized version of Float.compare(float1, float2) < 0.
    
    // Non-zero and non-NaN checking.
    if (float1 < float2) {
      return true;
    }
    if (float1 > float2) {
      return false;
    }
    if (float1 == float2 && 0.0f != float1) {
      return false;
    }
    
    // NaNs are equal to other NaNs and larger than any other float
    if (Float.isNaN(float1)) {
      return false;
    } else if (Float.isNaN(float2)) {
      return true;
    }
    
    // Deal with +0.0 and -0.0
    int f1 = Float.floatToRawIntBits(float1);
    int f2 = Float.floatToRawIntBits(float2);
    return f1 < f2;
  }
  
  private static <T> int med3(T[] array, int a, int b, int c, Comparator<T> comp) {
    T x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(byte[] array, int a, int b, int c, ByteComparator comp) {
    byte x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(char[] array, int a, int b, int c, CharComparator comp) {
    char x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(double[] array, int a, int b, int c,
      DoubleComparator comp) {
    double x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(float[] array, int a, int b, int c,
      FloatComparator comp) {
    float x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(int[] array, int a, int b, int c, IntComparator comp) {
    int x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(long[] array, int a, int b, int c, LongComparator comp) {
    long x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  private static int med3(short[] array, int a, int b, int c,
      ShortComparator comp) {
    short x = array[a], y = array[b], z = array[c];
    int comparisonxy = comp.compare(x, y);
    int comparisonxz = comp.compare(x, z);
    int comparisonyz = comp.compare(y, z);
    return comparisonxy < 0 ? (comparisonyz < 0 ? b
        : (comparisonxz < 0 ? c : a)) : (comparisonyz > 0 ? b
        : (comparisonxz > 0 ? c : a));
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the {@code byte} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @param comp
   *          the comparison that determines the sort.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static void quickSort(byte[] array, int start, int end,
      ByteComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void checkBounds(int arrLength, int start, int end) {
    if (start > end) {
      // K0033=Start index ({0}) is greater than end index ({1})
      throw new IllegalArgumentException("Start index " + start
          + " is greater than end index " + end);
    }
    if (start < 0) {
      throw new ArrayIndexOutOfBoundsException("Array index out of range "
          + start);
    }
    if (end > arrLength) {
      throw new ArrayIndexOutOfBoundsException("Array index out of range "
          + end);
    }
  }
  
  private static void quickSort0(int start, int end, byte[] array,
      ByteComparator comp) {
    byte temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j - 1], array[j]) > 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    byte partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(array[b], partionValue)) <= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the {@code char} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static void quickSort(char[] array, int start, int end,
      CharComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void quickSort0(int start, int end, char[] array,
      CharComparator comp) {
    char temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j - 1], array[j]) > 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    char partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(array[b], partionValue)) <= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the {@code double} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @param comp
   *          the comparison.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   * @see Double#compareTo(Double)
   */
  public static void quickSort(double[] array, int start, int end,
      DoubleComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void quickSort0(int start, int end, double[] array,
      DoubleComparator comp) {
    double temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j], array[j - 1]) < 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    double partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(partionValue, array[b])) >= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the {@code float} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @param comp
   *          the comparator.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static void quickSort(float[] array, int start, int end,
      FloatComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void quickSort0(int start, int end, float[] array,
      FloatComparator comp) {
    float temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j], array[j - 1]) < 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    float partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(partionValue, array[b])) >= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the {@code int} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @param comp
   *          the comparator.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static void quickSort(int[] array, int start, int end,
      IntComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void quickSort0(int start, int end, int[] array,
      IntComparator comp) {
    int temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j - 1], array[j]) > 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    int partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(array[b], partionValue)) <= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the {@code long} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @param comp
   *          the comparator.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static void quickSort(long[] array, int start, int end,
      LongComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void quickSort0(int start, int end, long[] array,
      LongComparator comp) {
    long temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j - 1], array[j]) > 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    long partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(array[b], partionValue)) <= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in a specified order.
   * 
   * @param array
   *          the array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @param comp
   *          the comparator.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static <T> void quickSort(T[] array, int start, int end,
      Comparator<T> comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private final static class ComparableAdaptor<T extends Comparable<? super T>>
      implements Comparator<T> {
    
    @Override
    public int compare(T o1, T o2) {
      return o1.compareTo(o2);
    }
    
  }
  
  /**
   * Sort the specified range of an array of object that implement the Comparable
   * interface.
   * @param <T> The type of object.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   */
  public static <T extends Comparable<? super T>> void quickSort(T[] array,
      int start, int end) {
    quickSort(array, start, end, new ComparableAdaptor<T>());
  }
  
  private static <T> void quickSort0(int start, int end, T[] array,
      Comparator<T> comp) {
    T temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j - 1], array[j]) > 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    T partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(array[b], partionValue)) <= 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) >= 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }
  
  /**
   * Sorts the specified range in the array in ascending numerical order.
   * 
   * @param array
   *          the {@code short} array to be sorted.
   * @param start
   *          the start index to sort.
   * @param end
   *          the last + 1 index to sort.
   * @throws IllegalArgumentException
   *           if {@code start > end}.
   * @throws ArrayIndexOutOfBoundsException
   *           if {@code start < 0} or {@code end > array.length}.
   */
  public static void quickSort(short[] array, int start, int end,
      ShortComparator comp) {
    if (array == null) {
      throw new NullPointerException();
    }
    checkBounds(array.length, start, end);
    quickSort0(start, end, array, comp);
  }
  
  private static void quickSort0(int start, int end, short[] array,
      ShortComparator comp) {
    short temp;
    int length = end - start;
    if (length < 7) {
      for (int i = start + 1; i < end; i++) {
        for (int j = i; j > start && comp.compare(array[j - 1], array[j]) > 0; j--) {
          temp = array[j];
          array[j] = array[j - 1];
          array[j - 1] = temp;
        }
      }
      return;
    }
    int middle = (start + end) / 2;
    if (length > 7) {
      int bottom = start;
      int top = end - 1;
      if (length > 40) {
        length /= 8;
        bottom = med3(array, bottom, bottom + length, bottom + (2 * length),
            comp);
        middle = med3(array, middle - length, middle, middle + length, comp);
        top = med3(array, top - (2 * length), top - length, top, comp);
      }
      middle = med3(array, bottom, middle, top, comp);
    }
    short partionValue = array[middle];
    int a, b, c, d;
    a = b = start;
    c = d = end - 1;
    while (true) {
      int comparison;
      while (b <= c && (comparison = comp.compare(array[b], partionValue)) < 0) {
        if (comparison == 0) {
          temp = array[a];
          array[a++] = array[b];
          array[b] = temp;
        }
        b++;
      }
      while (c >= b && (comparison = comp.compare(array[c], partionValue)) > 0) {
        if (comparison == 0) {
          temp = array[c];
          array[c] = array[d];
          array[d--] = temp;
        }
        c--;
      }
      if (b > c) {
        break;
      }
      temp = array[b];
      array[b++] = array[c];
      array[c--] = temp;
    }
    length = a - start < b - a ? a - start : b - a;
    int l = start;
    int h = b - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    length = d - c < end - 1 - d ? d - c : end - 1 - d;
    l = b;
    h = end - length;
    while (length-- > 0) {
      temp = array[l];
      array[l++] = array[h];
      array[h++] = temp;
    }
    if ((length = b - a) > 0) {
      quickSort0(start, start + length, array, comp);
    }
    if ((length = d - c) > 0) {
      quickSort0(end - length, end, array, comp);
    }
  }

  /**
   * Perform a merge sort on the specified range of an array.
   * 
   * @param <T> the type of object in the array.
   * @param array the array.
   * @param start first index. 
   * @param end last index (exclusive).
   * @param comp comparator object.
   */
  @SuppressWarnings("unchecked") // required to make the temp array work, afaict.
  public static <T> void mergeSort(T[] array, int start, int end,
      Comparator<T> comp) {
    checkBounds(array.length, start, end);
    int length = end - start;
    if (length <= 0) {
      return;
    }
    
    T[] out = (T[]) new Object[array.length];
    System.arraycopy(array, start, out, start, length);
    mergeSort(out, array, start, end, comp);
  }
  
  /**
   * Perform a merge sort of the specific range of an array of objects that implement
   * Comparable.
   * @param <T> the type of the objects in the array.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   */
  public static <T extends Comparable<? super T>> void mergeSort(T[] array, int start, int end) {
    mergeSort(array, start, end, new ComparableAdaptor<T>());
  }
  
  /**
   * Performs a sort on the section of the array between the given indices using
   * a mergesort with exponential search algorithm (in which the merge is
   * performed by exponential search). n*log(n) performance is guaranteed and in
   * the average case it will be faster then any mergesort in which the merge is
   * performed by linear search.
   * 
   * @param in
   *          - the array for sorting.
   * @param out
   *          - the result, sorted array.
   * @param start
   *          the start index
   * @param end
   *          the end index + 1
   * @param c
   *          - the comparator to determine the order of the array.
   */
  private static <T> void mergeSort(T[] in, T[] out, int start, int end,
      Comparator<T> c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        T current = out[i];
        T prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      T fromVal = in[start];
      T rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }
  
  /**
   * Finds the place of specified range of specified sorted array, where the
   * element should be inserted for getting sorted array. Uses exponential
   * search algorithm.
   * 
   * @param arr
   *          - the array with already sorted range
   * @param val
   *          - object to be inserted
   * @param l
   *          - the start index
   * @param r
   *          - the end index
   * @param bnd
   *          - possible values 0,-1. "-1" - val is located at index more then
   *          elements equals to val. "0" - val is located at index less then
   *          elements equals to val.
   * @param c
   *          - the comparator used to compare Objects
   */
  private static <T> int find(T[] arr, T val, int bnd, int l, int r,
      Comparator<T> c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static ByteComparator naturalByteComparison = new ByteComparator() {
    @Override
    public int compare(byte o1, byte o2) {
      return o1 - o2;
    }};
    
    /**
     * Perform a merge sort on a range of a byte array, using numerical order.
     * @param array the array.
     * @param start the first index.
     * @param end the last index (exclusive).
     */
  public static void mergeSort(byte[] array, int start, int end) {
    mergeSort(array, start, end, naturalByteComparison);
  }
  
  /**
   * Perform a merge sort on a range of a byte array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  public static void mergeSort(byte[] array, int start, int end, ByteComparator comp) {
    checkBounds(array.length, start, end);
    byte[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  private static void mergeSort(byte[] in, byte[] out, int start, int end,
      ByteComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        byte current = out[i];
        byte prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      byte fromVal = in[start];
      byte rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(byte[] arr, byte val, int bnd, int l, int r,
      ByteComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static CharComparator naturalCharComparison = new CharComparator() {
    @Override
    public int compare(char o1, char o2) {
      return o1 - o2;
    }};
    
    /**
     * Perform a merge sort on a range of a char array, using numerical order.
     * @param array the array.
     * @param start the first index.
     * @param end the last index (exclusive).
     */
  public static void mergeSort(char[] array, int start, int end) {
    mergeSort(array, start, end, naturalCharComparison);
  }

  /**
   * Perform a merge sort on a range of a char array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  public static void mergeSort(char[] array, int start, int end, CharComparator comp) {
    checkBounds(array.length, start, end);
    char[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  private static void mergeSort(char[] in, char[] out, int start, int end,
      CharComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        char current = out[i];
        char prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      char fromVal = in[start];
      char rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(char[] arr, char val, int bnd, int l, int r,
      CharComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static ShortComparator naturalShortComparison = new ShortComparator() {
    @Override
    public int compare(short o1, short o2) {
      return o1 - o2;
    }};
    
    /**
     * Perform a merge sort on a range of a short array, using numerical order.
     * @param array the array.
     * @param start the first index.
     * @param end the last index (exclusive).
     */
  public static void mergeSort(short[] array, int start, int end) {
    mergeSort(array, start, end, naturalShortComparison);
  }
  
  public static void mergeSort(short[] array, int start, int end, ShortComparator comp) {
    checkBounds(array.length, start, end);
    short[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  
  /**
   * Perform a merge sort on a range of a short array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  private static void mergeSort(short[] in, short[] out, int start, int end,
      ShortComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        short current = out[i];
        short prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      short fromVal = in[start];
      short rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(short[] arr, short val, int bnd, int l, int r,
      ShortComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static IntComparator naturalIntComparison = new IntComparator() {
    @Override
    public int compare(int o1, int o2) {
      return o1 < o2 ? -1 : o1 > o2 ? 1 : 0;
    }};
    
  public static void mergeSort(int[] array, int start, int end) {
    mergeSort(array, start, end, naturalIntComparison);
  }

  /**
   * Perform a merge sort on a range of a int array using numerical order.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  public static void mergeSort(int[] array, int start, int end, IntComparator comp) {
    checkBounds(array.length, start, end);
    int[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  /**
   * Perform a merge sort on a range of a int array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  private static void mergeSort(int[] in, int[] out, int start, int end,
      IntComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        int current = out[i];
        int prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      int fromVal = in[start];
      int rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(int[] arr, int val, int bnd, int l, int r,
      IntComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static LongComparator naturalLongComparison = new LongComparator() {
    @Override
    public int compare(long o1, long o2) {
      return o1 < o2 ? -1 : o1 > o2 ? 1 : 0;
    }};
    
    /**
     * Perform a merge sort on a range of a long array using numerical order.
     * @param array the array.
     * @param start the first index.
     * @param end the last index (exclusive).
     * @param comp the comparator object.
     */
  public static void mergeSort(long[] array, int start, int end) {
    mergeSort(array, start, end, naturalLongComparison);
  }

  /**
   * Perform a merge sort on a range of a long array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  public static void mergeSort(long[] array, int start, int end, LongComparator comp) {
    checkBounds(array.length, start, end);
    long[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  private static void mergeSort(long[] in, long[] out, int start, int end,
      LongComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        long current = out[i];
        long prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      long fromVal = in[start];
      long rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(long[] arr, long val, int bnd, int l, int r,
      LongComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static FloatComparator naturalFloatComparison = new FloatComparator() {
    @Override
    public int compare(float o1, float o2) {
      return Float.compare(o1, o2);
    }};
    
    /**
     * Perform a merge sort on a range of a float array using Float.compare for an ordering.
     * @param array the array.
     * @param start the first index.
     * @param end the last index (exclusive).
     * @param comp the comparator object.
     */
  public static void mergeSort(float[] array, int start, int end) {
    mergeSort(array, start, end, naturalFloatComparison);
  }

  /**
   * Perform a merge sort on a range of a float array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  public static void mergeSort(float[] array, int start, int end, FloatComparator comp) {
    checkBounds(array.length, start, end);
    float[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  private static void mergeSort(float[] in, float[] out, int start, int end,
      FloatComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        float current = out[i];
        float prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      float fromVal = in[start];
      float rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(float[] arr, float val, int bnd, int l, int r,
      FloatComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }
  
  private final static DoubleComparator naturalDoubleComparison = new DoubleComparator() {
    @Override
    public int compare(double o1, double o2) {
      return Double.compare(o1, o2);
    }};
    
    
    /**
     * Perform a merge sort on a range of a double array using a Double.compare as an ordering.
     * @param array the array.
     * @param start the first index.
     * @param end the last index (exclusive).
     * @param comp the comparator object.
     */
  public static void mergeSort(double[] array, int start, int end) {
    mergeSort(array, start, end, naturalDoubleComparison);
  }

  /**
   * Perform a merge sort on a range of a double array using a specified ordering.
   * @param array the array.
   * @param start the first index.
   * @param end the last index (exclusive).
   * @param comp the comparator object.
   */
  public static void mergeSort(double[] array, int start, int end, DoubleComparator comp) {
    checkBounds(array.length, start, end);
    double[] out = java.util.Arrays.copyOf(array, array.length);
    mergeSort(out, array, start, end, comp);
  }

  private static void mergeSort(double[] in, double[] out, int start, int end,
      DoubleComparator c) {
    int len = end - start;
    // use insertion sort for small arrays
    if (len <= SIMPLE_LENGTH) {
      for (int i = start + 1; i < end; i++) {
        double current = out[i];
        double prev = out[i - 1];
        if (c.compare(prev, current) > 0) {
          int j = i;
          do {
            out[j--] = prev;
          } while (j > start && (c.compare(prev = out[j - 1], current) > 0));
          out[j] = current;
        }
      }
      return;
    }
    int med = (end + start) >>> 1;
    mergeSort(out, in, start, med, c);
    mergeSort(out, in, med, end, c);
    
    // merging
    
    // if arrays are already sorted - no merge
    if (c.compare(in[med - 1], in[med]) <= 0) {
      System.arraycopy(in, start, out, start, len);
      return;
    }
    int r = med, i = start;
    
    // use merging with exponential search
    do {
      double fromVal = in[start];
      double rVal = in[r];
      if (c.compare(fromVal, rVal) <= 0) {
        int l_1 = find(in, rVal, -1, start + 1, med - 1, c);
        int toCopy = l_1 - start + 1;
        System.arraycopy(in, start, out, i, toCopy);
        i += toCopy;
        out[i++] = rVal;
        r++;
        start = l_1 + 1;
      } else {
        int r_1 = find(in, fromVal, 0, r + 1, end - 1, c);
        int toCopy = r_1 - r + 1;
        System.arraycopy(in, r, out, i, toCopy);
        i += toCopy;
        out[i++] = fromVal;
        start++;
        r = r_1 + 1;
      }
    } while ((end - r) > 0 && (med - start) > 0);
    
    // copy rest of array
    if ((end - r) <= 0) {
      System.arraycopy(in, start, out, i, med - start);
    } else {
      System.arraycopy(in, r, out, i, end - r);
    }
  }

  private static int find(double[] arr, double val, int bnd, int l, int r,
      DoubleComparator c) {
    int m = l;
    int d = 1;
    while (m <= r) {
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
        break;
      }
      m += d;
      d <<= 1;
    }
    while (l <= r) {
      m = (l + r) >>> 1;
      if (c.compare(val, arr[m]) > bnd) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return l - 1;
  }

}