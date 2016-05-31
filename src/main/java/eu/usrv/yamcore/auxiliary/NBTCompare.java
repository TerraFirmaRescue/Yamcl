
package eu.usrv.yamcore.auxiliary;


import eu.usrv.yamcore.auxiliary.enums.NBTCompareResult;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


/**
 * A class to help with all sorts of NBT-Tag comparing
 * 
 */
public class NBTCompare
{
  private NBTTagCompound _mCompound;

  /**
   * Initialize a NBT-compare instance from an ItemStack
   * 
   * @param pStack
   */
  public NBTCompare( ItemStack pStack )
  {
    if( pStack != null )
      _mCompound = (NBTTagCompound) pStack.getTagCompound().copy();
    else
      _mCompound = new NBTTagCompound();
  }

  /**
   * Initialize a NBT-compare instance from a generated/loaded NBTTagCompound
   * 
   * @param pTag
   */
  public NBTCompare( NBTTagCompound pTag )
  {
    if( pTag != null )
      _mCompound = (NBTTagCompound) pTag.copy();
    else
      _mCompound = new NBTTagCompound();
  }

  /**
   * {@link #hasTagWithValue(String, String)}
   */
  public boolean hasTagWithValue( String pTagName, int pValue )
  {
    return compareResultToBoolean( compareIntTag( pTagName, pValue ) );
  }

  /**
   * {@link #hasTagWithValue(String, String)}
   */
  public boolean hasTagWithValue( String pTagName, long pValue )
  {
    return compareResultToBoolean( compareLongTag( pTagName, pValue ) );
  }

  /**
   * {@link #hasTagWithValue(String, String)}
   */
  public boolean hasTagWithValue( String pTagName, float pValue )
  {
    return compareResultToBoolean( compareFloatTag( pTagName, pValue ) );
  }

  /**
   * {@link #hasTagWithValue(String, String)}
   */
  public boolean hasTagWithValue( String pTagName, double pValue )
  {
    return compareResultToBoolean( compareDoubleTag( pTagName, pValue ) );
  }

  /**
   * Check if NBTTag contains given tag, with given value
   * Please note: This function will also return false if no such tag was found!
   * 
   * @param pTagName Name of the NBTTag to check
   * @param pValue Value of the NBTTag to check
   * @return true if the value is equal to pValue, false if not
   * 
   */
  public boolean hasTagWithValue( String pTagName, String pValue )
  {
    return compareResultToBoolean( compareStringTag( pTagName, pValue ) );
  }

  public NBTCompareResult compareStringTag( String pTagName, String pValue )
  {
    NBTCompareResult tResult = NBTCompareResult.NoSuchTag;
    if( _mCompound.hasKey( pTagName ) )
    {
      if( _mCompound.getString( pTagName ) == pValue )
        tResult = NBTCompareResult.IsEqual;
      else
        tResult = NBTCompareResult.IsNotEqual;
    }

    return tResult;
  }

  /**
   * Compares a given TagName to a given TagValue, and returns the result as an Enum
   * 
   * @param pTagName The TagName to find
   * @param pValue The value to compare the tag to, if found
   * @return An enum-state representing the relation between pValue and stored NBT tag
   */
  public NBTCompareResult compareBoolTag( String pTagName, boolean pValue )
  {
    NBTCompareResult tResult = NBTCompareResult.NoSuchTag;
    if( _mCompound.hasKey( pTagName ) )
    {
      if( _mCompound.getBoolean( pTagName ) == pValue )
        tResult = NBTCompareResult.IsEqual;
      else
        tResult = NBTCompareResult.IsNotEqual;
    }

    return tResult;
  }

  /**
   * {@link #compareBoolTag(String, boolean)}
   */
  public NBTCompareResult compareIntTag( String pTagName, int pValue )
  {
    NBTCompareResult tResult = NBTCompareResult.NoSuchTag;
    if( _mCompound.hasKey( pTagName ) )
    {
      double tVal1 = (double) _mCompound.getInteger( pTagName );
      tResult = numericCompareToEnum( tVal1, pValue );
    }

    return tResult;
  }

  /**
   * {@link #compareBoolTag(String, boolean)}
   */
  public NBTCompareResult compareLongTag( String pTagName, long pValue )
  {
    NBTCompareResult tResult = NBTCompareResult.NoSuchTag;
    if( _mCompound.hasKey( pTagName ) )
    {
      double tVal1 = (double) _mCompound.getLong( pTagName );
      tResult = numericCompareToEnum( tVal1, pValue );
    }

    return tResult;
  }

  /**
   * {@link #compareBoolTag(String, boolean)}
   */
  public NBTCompareResult compareFloatTag( String pTagName, float pValue )
  {
    NBTCompareResult tResult = NBTCompareResult.NoSuchTag;
    if( _mCompound.hasKey( pTagName ) )
    {
      double tVal1 = (double) _mCompound.getFloat( pTagName );
      tResult = numericCompareToEnum( tVal1, pValue );
    }

    return tResult;
  }

  /**
   * {@link #compareBoolTag(String, boolean)}
   */
  public NBTCompareResult compareDoubleTag( String pTagName, double pValue )
  {
    NBTCompareResult tResult = NBTCompareResult.NoSuchTag;
    if( _mCompound.hasKey( pTagName ) )
    {
      double tVal1 = _mCompound.getDouble( pTagName );
      tResult = numericCompareToEnum( tVal1, pValue );
    }

    return tResult;
  }

  private NBTCompareResult numericCompareToEnum( double pVal1, double pVal2 )
  {
    NBTCompareResult tResult = NBTCompareResult.IsNotEqual;
    if( pVal1 == pVal2 )
      tResult = NBTCompareResult.IsEqual;
    else if( pVal1 < pVal2 )
      tResult = NBTCompareResult.IsLower;
    else if( pVal1 > pVal2 )
      tResult = NBTCompareResult.IsGreater;

    return tResult;
  }

  private boolean compareResultToBoolean( NBTCompareResult pResult )
  {
    boolean tFlag = false;

    if( pResult != NBTCompareResult.NoSuchTag )
      tFlag = pResult == NBTCompareResult.IsEqual ? true : false;

    return tFlag;
  }
}
