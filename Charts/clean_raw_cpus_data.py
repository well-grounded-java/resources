import os
import sys
import re
#import shutil

def clean_file(f_name):

    content_new = ''
    content_new2 = ''
    content_new3 = ''
    with open (f_name, 'r' ) as f:
        content = f.read()
        content_new = re.sub('\[\d+\]', '', content, flags = re.M)
        content_new = re.sub('\+', '', content_new, flags = re.M)
        content_new = re.sub(' ,', '|', content_new, flags = re.M)
        content_new = re.sub(',', '', content_new, flags = re.M)
        content_new = re.sub('\|', ',', content_new, flags = re.M)

    print(content_new)

#    with open (f_name_out, 'w' ) as f:
#        f.write(content_new)


if __name__ == "__main__":
    f_name = sys.argv[1]
    clean_file(f_name)
