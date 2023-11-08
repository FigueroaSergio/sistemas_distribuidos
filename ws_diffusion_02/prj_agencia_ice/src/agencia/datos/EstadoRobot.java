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

public class EstadoRobot implements java.lang.Cloneable,
                                    java.io.Serializable
{
    public String nombre;

    public int id;

    public String IORrob;

    public EstadoRobot()
    {
        this.nombre = "";
        this.IORrob = "";
    }

    public EstadoRobot(String nombre, int id, String IORrob)
    {
        this.nombre = nombre;
        this.id = id;
        this.IORrob = IORrob;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        EstadoRobot r = null;
        if(rhs instanceof EstadoRobot)
        {
            r = (EstadoRobot)rhs;
        }

        if(r != null)
        {
            if(this.nombre != r.nombre)
            {
                if(this.nombre == null || r.nombre == null || !this.nombre.equals(r.nombre))
                {
                    return false;
                }
            }
            if(this.id != r.id)
            {
                return false;
            }
            if(this.IORrob != r.IORrob)
            {
                if(this.IORrob == null || r.IORrob == null || !this.IORrob.equals(r.IORrob))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::agencia::datos::EstadoRobot");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, nombre);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, id);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, IORrob);
        return h_;
    }

    public EstadoRobot clone()
    {
        EstadoRobot c = null;
        try
        {
            c = (EstadoRobot)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.nombre);
        ostr.writeInt(this.id);
        ostr.writeString(this.IORrob);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.nombre = istr.readString();
        this.id = istr.readInt();
        this.IORrob = istr.readString();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, EstadoRobot v)
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

    static public EstadoRobot ice_read(com.zeroc.Ice.InputStream istr)
    {
        EstadoRobot v = new EstadoRobot();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<EstadoRobot> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, EstadoRobot v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<EstadoRobot> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(EstadoRobot.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final EstadoRobot _nullMarshalValue = new EstadoRobot();

    /** @hidden */
    public static final long serialVersionUID = 2376654137930139316L;
}