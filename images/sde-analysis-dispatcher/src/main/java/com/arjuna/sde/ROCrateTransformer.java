package com.arjuna.sde;

import java.util.Set;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;

import edu.kit.datamanager.ro_crate.RoCrate;
import edu.kit.datamanager.ro_crate.reader.RoCrateReader;
import edu.kit.datamanager.ro_crate.reader.ZipReader;
import edu.kit.datamanager.ro_crate.writer.RoCrateWriter;
import edu.kit.datamanager.ro_crate.writer.ZipWriter;

public class ROCrateTransformer
{
    static public RoCrate zipBytesToROC(byte[] zipBytes)
        throws IOException
    {
        Set<PosixFilePermission>                perms            = PosixFilePermissions.fromString("rw-rw-rw-");
        FileAttribute<Set<PosixFilePermission>> attr             = PosixFilePermissions.asFileAttribute(perms);
        java.nio.file.Path                      tempZipPath      = Files.createTempFile("temp_ro-crate", ".zip", attr);
        Files.write(tempZipPath, zipBytes);
        RoCrateReader                           roCrateZipReader = new RoCrateReader(new ZipReader());
        RoCrate                                 roCrate          = roCrateZipReader.readCrate(tempZipPath.toString());
        tempZipPath.toFile().delete();

        return roCrate;
    }

    static public byte[] rocToZipBytes(RoCrate roCrate)
        throws IOException
    {
        Set<PosixFilePermission>                perms            = PosixFilePermissions.fromString("rw-rw-rw-");
        FileAttribute<Set<PosixFilePermission>> attr             = PosixFilePermissions.asFileAttribute(perms);
        java.nio.file.Path                      tempZipPath	 = Files.createTempFile("temp_ro-crate", ".zip", attr);
        RoCrateWriter                           roCrateZipWriter = new RoCrateWriter(new ZipWriter());
        roCrateZipWriter.save(roCrate, tempZipPath.toString());
        byte[] roCrateBytes = Files.readAllBytes(tempZipPath);
        tempZipPath.toFile().delete();

        return roCrateBytes;
    }
}
