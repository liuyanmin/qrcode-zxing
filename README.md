# qrcode-zxing
<h2>基于google开源的zxing生成和解析二维码，可以生成带logo的二维码</h2>
<br>

本项目主要是基于zxing生成和解析二维码，代码非常简单，主要是对zxing api的使用。项目中主要提供了两个工具类（EncodeQrCodeUtil.java和DecodeQrCodeUtil.java）用户生成和解析二维码。
<br><br>

<h3>主要功能：</h3><br/>
&nbsp;&nbsp;1、生成普通二维码<br>
&nbsp;&nbsp;2、生成带logo二维码<br>
&nbsp;&nbsp;3、解析二维码<br>
<br/>

<h3>EncodeQrCodeUtil类方法描述：</h3><br>
1、生成二维码（带logo）<br>
<code>public static BufferedImage createQrCode(String content, int width, int height, String logoImgPath, Integer logoWidth, Integer logoHeight, boolean needCompress) throws WriterException, IOException</code><br/><br/>

2、生成带logo的二维码（指定logo宽高）<br/>
<code>public static void encode(String content, int width, int height, String logoImgPath, Integer logoWidth, Integer logoHeight, String destPath, boolean needCompress) throws WriterException, IOException</code><br/>

3、生成带logo的二维码（不指定logo宽高，使用logo默认宽高）<br/>
<code>public static void encode(String content, int width, int height, String logoImgPath, String destPath, boolean needCompress) throws WriterException, IOException</code><br/>

4、生成不带logo的二维码<br/>
<code>public static void encode(String content, int width, int height, String destPath) throws WriterException, IOException</code><br/>

5、生成带logo的二维码，并输出到指定的输出流（指定logo宽高）<br/>
<code>public static void encode(String content, int width, int height, String logoImgPath, Integer logoWidth, Integer logoHeight, OutputStream output, boolean needCompress) throws WriterException, IOException</code><br/>

6、生成带logo的二维码，并输出到指定的输出流（不指定logo宽高）<br/>
<code>public static void encode(String content, int width, int height, String logoImgPath, OutputStream output, boolean needCompress) throws WriterException, IOException</code><br/>

7、生成不带参数的二维码输出到指定的输出流<br/>
<code>public static void encode(String content, int width, int height, OutputStream output) throws WriterException, IOException</code><br/>
<br/>


<h3>DecodeQrCodeUtil类方法描述：</h3><br>
1、二维码解析（根据File）<br/>
<code>public static String decode(File file) throws IOException, NotFoundException</code><br/>

2、二维码解析（根据path）<br/>
<code>public static String decode(String path) throws IOException, NotFoundException</code><br/>
<br/>

<h3>注意：</h3>
<span style="color:read">二维码存储的内容和解析出的内容直接转换成字符串了，默认使用的是UTF-8编码，要是二维码中保存汉字会其他字符的时候根据需要修改编码。</span><br/>

