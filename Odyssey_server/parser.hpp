//
// Created by elias on 14/05/18.
//

#ifndef ODYSSEY_SERVER_PARSER_HPP
#define ODYSSEY_SERVER_PARSER_HPP

#endif //ODYSSEY_SERVER_PARSER_HPP

/**
04
 *  @file
05
 *  Class "GetConfig" provides the functions to read the XML data.
06
 *  @version 1.0
07
 */

#include <xercesc/dom/DOM.hpp>
#include <xercesc/dom/DOMDocument.hpp>
#include <xercesc/dom/DOMDocumentType.hpp>
#include <xercesc/dom/DOMElement.hpp>
#include <xercesc/dom/DOMImplementation.hpp>
#include <xercesc/dom/DOMImplementationLS.hpp>
#include <xercesc/dom/DOMNodeIterator.hpp>
#include <xercesc/dom/DOMNodeList.hpp>
#include <xercesc/dom/DOMText.hpp>

#include <xercesc/parsers/XercesDOMParser.hpp>
#include <xercesc/util/XMLUni.hpp>

#include <string>
#include <stdexcept>
