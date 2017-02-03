package com.edu.knoldus

import java.io._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FileMining {

  //this function takes  directory as input and calls recursive drillDownToDirectory method
  def beginWithDirectory(directory: String): Future[List[File]] = {

    def drillDownToDirectory(currentDirectoryName: String, listOfAllFileFound: List[File]): List[File] = {

      val currentWorkingDirectory = new File(currentDirectoryName)

      if (currentWorkingDirectory.exists && currentWorkingDirectory.isDirectory) {
        val allFilesAndFolders = currentWorkingDirectory.listFiles.toList

        val directoryDrillDownResult = allFilesAndFolders map (inContainer =>
          if (inContainer.isFile){ listOfAllFileFound :+ inContainer}
          else {drillDownToDirectory(inContainer.toString, listOfAllFileFound)}
          )
        directoryDrillDownResult.flatten
      }
      else {
        listOfAllFileFound
      }

    }

    Future {
      drillDownToDirectory(directory, List())
    }

  }

}
