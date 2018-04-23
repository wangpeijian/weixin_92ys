/**
 * Created by admin on 2016/3/12.
 */

var webpack = require('webpack');
var path = require('path');

var node_modules = path.resolve(__dirname, 'node_modules');
var pathToReact = path.resolve(node_modules, 'react/dist/react.min.js');

var ExtractTextPlugin = require("extract-text-webpack-plugin");
var CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;
var HtmlWebpackPlugin = require('html-webpack-plugin');

var WebpackMd5Hash = require('webpack-md5-hash');


//process.env.NODE_ENV = "production";

var isPublish = process.env.NODE_ENV === "production";

module.exports = {
    module: {
        loaders: [
            {
                test: /\.js?$/,
                exclude: /node_modules/,
	            include: [
		            path.resolve(__dirname, "src/"),
	            ],
                loader: 'babel',
                query: {
                    presets: ['es2015','react'],
                    plugins: ["transform-class-properties"],
                }
            },
            {
                test: /\.scss$/,
                loader: ExtractTextPlugin.extract("style", "css!sass"),
            },
            {
                test: /\.(png|jpg|gif)$/,
                loader: 'url?limit=10000&name=[path][name].[ext]',
            },
        ],

        noParse: [pathToReact]
    },
	
	devServer:{
		hot: true,
		inline: true,
	},

    resolve:{
        extensions:['','.js','.json',".scss"],
    },

    plugins: (()=>{
    	
    	var basePlugins = [
		    new WebpackMd5Hash(),
    		
		     new webpack.ProvidePlugin({
			    'fetch': 'imports?this=>global!exports?global.fetch!whatwg-fetch'
		    }),
		
		    new CommonsChunkPlugin({
			    name: ['react'],
			    minChunks: Infinity
		    }),
		
		    new HtmlWebpackPlugin({
			    filename: 'index.html',
			    template:'template.html',
			    inject: 'body',
			    minify: false,
			    hash: true,
			    cache: false,
			    showErrors: true,
			    chunks: ["react", "index" ,"style"],
			    favicon: './favicon.ico'
		    }),
	    ];
	
	    var proPlugins = [
	    	new webpack.DefinePlugin({
			    'process.env': {
				    NODE_ENV: JSON.stringify(process.env.NODE_ENV),
			    },
	        }),
		   
		     new webpack.optimize.UglifyJsPlugin({
			    output: {
				    comments: false,  // remove all comments
			    },
			    compress: {
				    warnings: false
			    }
		    }),
		    
		    new webpack.optimize.DedupePlugin(),
		    
		    new webpack.optimize.OccurrenceOrderPlugin(),
        ];
    	
    	if(isPublish){
		    return basePlugins.concat(proPlugins, new ExtractTextPlugin("style.[contenthash:8].css"));
	    }else{
    		return basePlugins.concat(new ExtractTextPlugin("style.css"));
	    }
	
    })(),

    entry: {
        index: './src/index.js',
	    react: ["react"],
    },
	
    output: (()=>{
	
	    var parameter = {
		    path: './assets',
		    filename: '[name].js',  //.[chunkhash:8]
		    chunkFilename: "chunks-[name]-[chunkhash:8].js"
	    }
	
	    if(isPublish){
		    parameter.filename = '[name].[chunkhash:8].js';
	    }
	
	    return parameter;
	
    })(),
};
