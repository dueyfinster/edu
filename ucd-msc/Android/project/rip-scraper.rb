#!/usr/bin/env ruby
#Encoding: UTF-8
require 'nokogiri'
require 'net/http'
require 'open-uri' 
require 'json' 
require 'geocoder'

BASE_URL = "http://rip.ie/showdn.php?dn=" 


def scrape(results, id)
  begin
    page = Nokogiri::HTML(open("#{BASE_URL}#{id}", :proxy => nil))
    lname, fname = page.css("ul.notice_navigation > li:nth-child(4)").text.split(",")
    first_name = fname.strip
    lname = lname.strip
    last_name = lname[0] + lname[1..-1].downcase
    address = page.css("span.small_addr").text
    coords =  Geocoder.coordinates(address)
    lat = coords[0].to_s
    lon = coords[1].to_s
    text = page.css("td.minhide").text
    date_death = Date.parse(page.css("div.ddeath").text.gsub('Date of Death: ', ''))
    date_publ = Date.parse(page.css("div.dpubl").text.gsub('Date Published: ',''))

    results << {"id"=> id, "first_name" => first_name, "last_name" => last_name, "address" => address, "lat" => lat, "lon" => lon, "text" => text,"date_of_death" =>  date_death, "date_published" => date_publ}
  rescue OpenURI::HTTPError => err
    puts "could not retrieve death notice from: #{BASE_URL}#{id} with error: #{err}"
  end
end

def output_to_json(results)
  jsonString = JSON.dump(results)
  File.open("ripapp/app/src/main/assets/death_notices.json","w") do |f|
    f.write(jsonString)
  end
end

def main
  results = [] 
  start_id = 250900 #250412
  end_id = 250944
  for i in start_id..end_id
    scrape(results, i)
  end
  #puts results.inspect
  output_to_json(results)
end

main
