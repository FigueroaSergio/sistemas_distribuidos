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

public class Escenario implements java.lang.Cloneable,
                                  java.io.Serializable
{
    public Rectangulo[] rects;

    public int nrecs;

    public int color;

    public Escenario()
    {
    }

    public Escenario(Rectangulo[] rects, int nrecs, int color)
    {
        this.rects = rects;
        this.nrecs = nrecs;
        this.color = color;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Escenario r = null;
        if(rhs instanceof Escenario)
        {
            r = (Escenario)rhs;
        }

        if(r != null)
        {
            if(!java.util.Arrays.equals(this.rects, r.rects))
            {
                return false;
            }
            if(this.nrecs != r.nrecs)
            {
                return false;
            }
            if(this.color != r.color)
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
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::agencia::datos::Escenario");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, rects);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, nrecs);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, color);
        return h_;
    }

    public Escenario clone()
    {
        Escenario c = null;
        try
        {
            c = (Escenario)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ListaRectangulosHelper.write(ostr, this.rects);
        ostr.writeInt(this.nrecs);
        ostr.writeInt(this.color);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.rects = ListaRectangulosHelper.read(istr);
        this.nrecs = istr.readInt();
        this.color = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Escenario v)
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

    static public Escenario ice_read(com.zeroc.Ice.InputStream istr)
    {
        Escenario v = new Escenario();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Escenario> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Escenario v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Escenario> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Escenario.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Escenario _nullMarshalValue = new Escenario();

    /** @hidden */
    public static final long serialVersionUID = -8612230453856646421L;
}