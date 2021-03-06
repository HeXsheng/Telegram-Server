/*
 *     This file is part of Telegram Server
 *     Copyright (C) 2015  Aykut Alparslan KOÇ
 *
 *     Telegram Server is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Telegram Server is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.telegram.tl.pq;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.TLObject;

/**
 * Created by aykut on 24/10/15.
 */
public class p_q_inner_data_temp extends TLObject{
    public static final int ID = 0x3c6a84d4;
    public byte[] pq;
    public byte[] p;
    public byte[] q;
    public byte[] nonce;
    public byte[] server_nonce;
    public byte[] new_nonce;
    public int expires_in;

    public p_q_inner_data_temp(){

    }

    public p_q_inner_data_temp(byte[] pq, byte[] p, byte[] q, byte[] nonce, byte[] server_nonce, byte[] new_nonce, int expires_in){
        this.pq = pq;
        this.p = p;
        this.q = q;
        if (nonce == null || nonce.length != 16) {
            throw new IllegalArgumentException("must be 16 bytes");
        }
        this.nonce = nonce;
        if (server_nonce == null || server_nonce.length != 16) {
            throw new IllegalArgumentException("must be 16 bytes");
        }
        this.server_nonce = server_nonce;
        if (new_nonce == null || new_nonce.length != 32) {
            throw new IllegalArgumentException("must be 32 bytes");
        }
        this.new_nonce = new_nonce;
        this.expires_in = expires_in;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        pq = buffer.readBytes();
        p = buffer.readBytes();
        q = buffer.readBytes();
        nonce = buffer.read(16);
        server_nonce = buffer.read(16);
        new_nonce = buffer.read(32);
        expires_in = buffer.readInt();
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(128);
        serializeTo(buffer);
        return buffer;
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeBytes(pq);
        buff.writeBytes(p);
        buff.writeBytes(q);
        buff.write(nonce);
        buff.write(server_nonce);
        buff.write(new_nonce);
        buff.writeInt(expires_in);
    }

    @Override
    public int getConstructor() {
        return ID;
    }
}
