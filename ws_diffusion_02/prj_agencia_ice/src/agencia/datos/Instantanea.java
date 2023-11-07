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

public class Instantanea implements java.lang.Cloneable,
                                    java.io.Serializable
{
    public EstadoRobot[] estadorobs;

    public Instantanea()
    {
    }

    public Instantanea(EstadoRobot[] estadorobs)
    {
        this.estadorobs = estadorobs;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Instantanea r = null;
        if(rhs instanceof Instantanea)
        {
            r = (Instantanea)rhs;
        }

        if(r != null)
        {
            if(!java.util.Arrays.equals(this.estadorobs, r.estadorobs))
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::agencia::datos::Instantanea");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, estadorobs);
        return h_;
    }

    public Instantanea clone()
    {
        Instantanea c = null;
        try
        {
            c = (Instantanea)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ListaEstadosRobotHelper.write(ostr, this.estadorobs);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.estadorobs = ListaEstadosRobotHelper.read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Instantanea v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Instantanea ice_read(com.zeroc.Ice.InputStream istr)
    {
        Instantanea v = new Instantanea();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Instantanea> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Instantanea v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Instantanea> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Instantanea.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Instantanea _nullMarshalValue = new Instantanea();

    /** @hidden */
    public static final long serialVersionUID = 903630538880502867L;
}
