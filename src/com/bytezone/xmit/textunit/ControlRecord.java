package com.bytezone.xmit.textunit;

import java.util.ArrayList;
import java.util.List;

import com.bytezone.xmit.Utility;

public class ControlRecord
{
  private final String name;
  private final List<TextUnit> textUnits = new ArrayList<> ();
  private final int fileNbr;

  // ---------------------------------------------------------------------------------//
  // constructor
  // ---------------------------------------------------------------------------------//

  public ControlRecord (byte[] buffer)
  {
    int ptr = 0;
    name = Utility.getString (buffer, ptr, 6);
    assert name.startsWith ("INMR0");
    int max = ptr + buffer.length;
    ptr += 6;

    if ("INMR02".equals (name))
    {
      fileNbr = (int) Utility.getFourBytes (buffer, ptr);     // need to save this
      ptr += 4;
    }
    else
      fileNbr = 0;

    while (ptr < max)
    {
      TextUnit textUnit = createTextUnit (buffer, ptr);
      if (textUnit == null)
        break;
      textUnits.add (textUnit);
      ptr += 4 + textUnit.length ();
    }
  }

  // ---------------------------------------------------------------------------------//
  // nameMatches
  // ---------------------------------------------------------------------------------//

  public boolean nameMatches (String name)
  {
    return this.name.equals (name);
  }

  // ---------------------------------------------------------------------------------//
  // fileNbrMatches
  // ---------------------------------------------------------------------------------//

  public boolean fileNbrMatches (int fileNbr)
  {
    return this.fileNbr == fileNbr;
  }

  // ---------------------------------------------------------------------------------//
  // createTextUnit
  // ---------------------------------------------------------------------------------//

  private TextUnit createTextUnit (byte[] buffer, int ptr)
  {
    int key = Utility.getTwoBytes (buffer, ptr);
    switch (key)
    {
      case TextUnit.INMDSNAM:
        return new Dsnam (buffer, ptr);

      case TextUnit.INMUTILN:
      case TextUnit.INMFNODE:
      case TextUnit.INMFUID:
      case TextUnit.INMTNODE:
      case TextUnit.INMTUID:
      case TextUnit.INMUSERP:
        return new TextUnitString (buffer, ptr);

      case TextUnit.INMNUMF:
      case TextUnit.INMLRECL:
      case TextUnit.INMBLKSZ:
      case TextUnit.INMSIZE:
      case TextUnit.INMDIR:
      case TextUnit.INMEATTR:
        return new TextUnitNumber (buffer, ptr);

      case TextUnit.INMFTIME:
        return new TextUnitTime (buffer, ptr);

      case TextUnit.INMRECFM:
        return new Recfm (buffer, ptr);

      case TextUnit.INMDSORG:
        return new Dsorg (buffer, ptr);

      case TextUnit.INMMEMBR:
        return new Member (buffer, ptr);

      case TextUnit.INMTYPE:
        return new DatasetType (buffer, ptr);

      case TextUnit.INMFACK:
      case TextUnit.INMTERM:
        return new TextUnit (buffer, ptr);

      default:
        System.out.printf ("Unknown key: %04X in %s%n", key, name);
        return new TextUnit (buffer, ptr);
    }
  }

  // ---------------------------------------------------------------------------------//
  // getTextUnit
  // ---------------------------------------------------------------------------------//

  public TextUnit getTextUnit (int keyId)
  {
    for (TextUnit textUnit : textUnits)
      if (textUnit.matches (keyId))
        return textUnit;

    return null;
  }

  // ---------------------------------------------------------------------------------//
  // toString
  // ---------------------------------------------------------------------------------//

  @Override
  public String toString ()
  {
    StringBuilder text = new StringBuilder ();

    String fileNbrText = fileNbr > 0 ? String.format (" (file %d)", fileNbr) : "";
    text.append (String.format ("Control Record: %s %s%n%n", name, fileNbrText));
    for (TextUnit textUnit : textUnits)
      text.append (textUnit + "\n");

    return text.toString ();
  }
}
