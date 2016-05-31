
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
  NBTTagCompound _mCompound;

  public NBTCompare( ItemStack pStack )
  {
    if( pStack != null )
      _mCompound = (NBTTagCompound) pStack.getTagCompound().copy();
    else
      _mCompound = new NBTTagCompound();
  }

  public NBTCompare( NBTTagCompound pTag )
  {
    if( pTag != null )
      _mCompound = (NBTTagCompound) pTag.copy();
    else
      _mCompound = new NBTTagCompound();
  }

  public boolean hasTagWithValue( String pTagName, int pValue )
  {
    return compareResultToBoolean( compareIntTag( pTagName, pValue ) );
  }

  public boolean hasTagWithValue( String pTagName, long pValue )
  {
    return compareResultToBoolean( compareLongTag( pTagName, pValue ) );
  }

  public boolean hasTagWithValue( String pTagName, float pValue )
  {
    return compareResultToBoolean( compareFloatTag( pTagName, pValue ) );
  }

  public boolean hasTagWithValue( String pTagName, double pValue )
  {
    return compareResultToBoolean( compareDoubleTag( pTagName, pValue ) );
  }

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
