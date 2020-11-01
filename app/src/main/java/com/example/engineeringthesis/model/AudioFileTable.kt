package com.example.engineeringthesis.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

data class AudioFileTable(
        @JsonProperty("streamId")
        @JsonIgnore
        val  streamId:String,

        @JsonProperty("fileStream")
        val  fileStream:ByteArray,

        @JsonProperty("audioFileTableName")
        val audioFileTableName:String,

        @JsonProperty("pathLocator")
        val  pathLocator:ByteArray,

        @JsonProperty("parentPathLocator")
        val  parentPathLocator:ByteArray,

        @JsonProperty("fileType")
        val  fileType:String,

        @JsonProperty("cachedFileSize")
        val  cachedFileSize:Long,

        @JsonProperty("creationTime")
        val  creationTime: OffsetDateTime,

        @JsonProperty("lastWriteTime")
        val  lastWriteTime: OffsetDateTime,

        @JsonProperty("lastAccessTime")
        val  lastAccessTime: OffsetDateTime,

        @JsonProperty("isNew")
        val isNew: Boolean,

        @JsonProperty("isOffline")
        val isOffline: Boolean,

        @JsonProperty("isReadonly")
        val isReadonly: Boolean,

        @JsonProperty("isArchive")
        val isArchive: Boolean,

        @JsonProperty("isSystem")
        val isSystem: Boolean,

        @JsonProperty("isTemporary")
        val isTemporary: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioFileTable

        if (streamId != other.streamId) return false
        if (!fileStream.contentEquals(other.fileStream)) return false
        if (audioFileTableName != other.audioFileTableName) return false
        if (!pathLocator.contentEquals(other.pathLocator)) return false
        if (!parentPathLocator.contentEquals(other.parentPathLocator)) return false
        if (fileType != other.fileType) return false
        if (cachedFileSize != other.cachedFileSize) return false
        if (creationTime != other.creationTime) return false
        if (lastWriteTime != other.lastWriteTime) return false
        if (lastAccessTime != other.lastAccessTime) return false
        if (isNew != other.isNew) return false
        if (isOffline != other.isOffline) return false
        if (isReadonly != other.isReadonly) return false
        if (isArchive != other.isArchive) return false
        if (isSystem != other.isSystem) return false
        if (isTemporary != other.isTemporary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = streamId.hashCode()
        result = 31 * result + fileStream.contentHashCode()
        result = 31 * result + audioFileTableName.hashCode()
        result = 31 * result + pathLocator.contentHashCode()
        result = 31 * result + parentPathLocator.contentHashCode()
        result = 31 * result + fileType.hashCode()
        result = 31 * result + cachedFileSize.hashCode()
        result = 31 * result + creationTime.hashCode()
        result = 31 * result + lastWriteTime.hashCode()
        result = 31 * result + lastAccessTime.hashCode()
        result = 31 * result + isNew.hashCode()
        result = 31 * result + isOffline.hashCode()
        result = 31 * result + isReadonly.hashCode()
        result = 31 * result + isArchive.hashCode()
        result = 31 * result + isSystem.hashCode()
        result = 31 * result + isTemporary.hashCode()
        return result
    }

    override fun toString(): String {
        return "AudioFileTable(streamId='$streamId', fileStream=${fileStream.contentToString()}, audioFileTableName='$audioFileTableName', pathLocator=${pathLocator.contentToString()}, parentPathLocator=${parentPathLocator.contentToString()}, fileType='$fileType', cachedFileSize=$cachedFileSize, creationTime=$creationTime, lastWriteTime=$lastWriteTime, lastAccessTime=$lastAccessTime, isNew=$isNew, isOffline=$isOffline, isReadonly=$isReadonly, isArchive=$isArchive, isSystem=$isSystem, isTemporary=$isTemporary)"
    }

}