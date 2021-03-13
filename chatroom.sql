USE [master]
GO
/****** Object:  Database [chatroom]    Script Date: 2021/3/13 16:11:49 ******/
CREATE DATABASE [chatroom]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'chatroom', FILENAME = N'E:\yswDB\chatroom\chatroom.mdf' , SIZE = 8192KB , MAXSIZE = 1048576KB , FILEGROWTH = 10%)
 LOG ON 
( NAME = N'chatroom_log', FILENAME = N'E:\yswDB\chatroom\chatroom_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 2048KB )
GO
ALTER DATABASE [chatroom] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [chatroom].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [chatroom] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [chatroom] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [chatroom] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [chatroom] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [chatroom] SET ARITHABORT OFF 
GO
ALTER DATABASE [chatroom] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [chatroom] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [chatroom] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [chatroom] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [chatroom] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [chatroom] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [chatroom] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [chatroom] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [chatroom] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [chatroom] SET  DISABLE_BROKER 
GO
ALTER DATABASE [chatroom] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [chatroom] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [chatroom] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [chatroom] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [chatroom] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [chatroom] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [chatroom] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [chatroom] SET RECOVERY FULL 
GO
ALTER DATABASE [chatroom] SET  MULTI_USER 
GO
ALTER DATABASE [chatroom] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [chatroom] SET DB_CHAINING OFF 
GO
ALTER DATABASE [chatroom] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [chatroom] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [chatroom] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'chatroom', N'ON'
GO
ALTER DATABASE [chatroom] SET QUERY_STORE = OFF
GO
USE [chatroom]
GO
/****** Object:  Table [dbo].[MessageInfo]    Script Date: 2021/3/13 16:11:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MessageInfo](
	[Sender] [varchar](10) NULL,
	[Receiver] [varchar](10) NULL,
	[Message] [varchar](50) NULL,
	[OnlineCount] [varchar](50) NULL,
	[Status] [varchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[userinfo]    Script Date: 2021/3/13 16:11:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[userinfo](
	[username] [varchar](10) NOT NULL,
	[password] [varchar](6) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [chatroom] SET  READ_WRITE 
GO
