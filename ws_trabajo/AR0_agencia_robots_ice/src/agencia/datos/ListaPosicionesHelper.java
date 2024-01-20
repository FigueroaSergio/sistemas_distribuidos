//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.9
//
// <auto-generated>
//
// Generated from file `robot.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package agencia.datos;

/**
 * Helper class for marshaling/unmarshaling ListaPosiciones.
 **/
public final class ListaPosicionesHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, Posicion[] v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.length);
            for(int i0 = 0; i0 < v.length; i0++)
            {
                Posicion.ice_write(ostr, v[i0]);
            }
        }
    }

    public static Posicion[] read(com.zeroc.Ice.InputStream istr)
    {
        final Posicion[] v;
        final int len0 = istr.readAndCheckSeqSize(8);
        v = new Posicion[len0];
        for(int i0 = 0; i0 < len0; i0++)
        {
            v[i0] = Posicion.ice_read(istr);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Posicion[]> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, Posicion[] v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            final int optSize = v == null ? 0 : v.length;
            ostr.writeSize(optSize > 254 ? optSize * 8 + 5 : optSize * 8 + 1);
            ListaPosicionesHelper.write(ostr, v);
        }
    }

    public static java.util.Optional<Posicion[]> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            Posicion[] v;
            v = ListaPosicionesHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
