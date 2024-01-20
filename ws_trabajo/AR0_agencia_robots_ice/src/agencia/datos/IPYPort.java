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

public class IPYPort implements java.lang.Cloneable,
                                java.io.Serializable
{
    public String ip;

    public int port;

    public IPYPort()
    {
        this.ip = "";
    }

    public IPYPort(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        IPYPort r = null;
        if(rhs instanceof IPYPort)
        {
            r = (IPYPort)rhs;
        }

        if(r != null)
        {
            if(this.ip != r.ip)
            {
                if(this.ip == null || r.ip == null || !this.ip.equals(r.ip))
                {
                    return false;
                }
            }
            if(this.port != r.port)
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
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::agencia::datos::IPYPort");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, ip);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, port);
        return h_;
    }

    public IPYPort clone()
    {
        IPYPort c = null;
        try
        {
            c = (IPYPort)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.ip);
        ostr.writeInt(this.port);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.ip = istr.readString();
        this.port = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, IPYPort v)
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

    static public IPYPort ice_read(com.zeroc.Ice.InputStream istr)
    {
        IPYPort v = new IPYPort();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<IPYPort> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, IPYPort v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<IPYPort> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(IPYPort.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final IPYPort _nullMarshalValue = new IPYPort();

    /** @hidden */
    public static final long serialVersionUID = 1405574484053791577L;
}