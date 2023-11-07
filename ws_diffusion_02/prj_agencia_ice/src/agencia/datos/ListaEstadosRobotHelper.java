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
 * Helper class for marshaling/unmarshaling ListaEstadosRobot.
 **/
public final class ListaEstadosRobotHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, EstadoRobot[] v)
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
                EstadoRobot.ice_write(ostr, v[i0]);
            }
        }
    }

    public static EstadoRobot[] read(com.zeroc.Ice.InputStream istr)
    {
        final EstadoRobot[] v;
        final int len0 = istr.readAndCheckSeqSize(6);
        v = new EstadoRobot[len0];
        for(int i0 = 0; i0 < len0; i0++)
        {
            v[i0] = EstadoRobot.ice_read(istr);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<EstadoRobot[]> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, EstadoRobot[] v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ListaEstadosRobotHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<EstadoRobot[]> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            EstadoRobot[] v;
            v = ListaEstadosRobotHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
